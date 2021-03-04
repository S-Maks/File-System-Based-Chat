package file.system.based.chat.repository.impl;

import file.system.based.chat.model.Message;
import file.system.based.chat.model.User;
import file.system.based.chat.repository.MessageRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static file.system.based.chat.repository.impl.UserRepositoryImpl.NAME_CATALOG_DATABASE;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    public static final String FILE_CATALOG_DATABASE_MESSAGES = "database/messages";
    private File file = new File(NAME_CATALOG_DATABASE);

    @PostConstruct
    public void init(){
        file.mkdir();
        file = new File(FILE_CATALOG_DATABASE_MESSAGES);
        file.mkdir();
    }

    @Override
    public void save(Message message) {
        File findFile = new File(FILE_CATALOG_DATABASE_MESSAGES);
        Set<String> files = loadToSetNamesFiles(findFile);
        if(addToCreatedFile(files,message)){
            createAndAddToFile(message);
        }
    }

    @Override
    public void edit(Message message) {
        File findFile = new File(FILE_CATALOG_DATABASE_MESSAGES);
        Set<String> files = loadToSetNamesFiles(findFile);
        addToCreatedFile(files,message);
    }

    @Override
    public void delete(Message message) {

    }

    private Set<String> loadToSetNamesFiles(File findFile){
        return  Arrays.stream(Objects.requireNonNull(findFile.list()))
                .parallel()
                .map(file -> FILE_CATALOG_DATABASE_MESSAGES + "/" + file)
                .filter(s -> Files.isDirectory(Paths.get(s))).collect(Collectors.toSet());

    }

    private boolean addToCreatedFile(Set<String> files, Message message){
        if(!files.isEmpty()){
            for (String fileName : files) {
                if (fileName.endsWith(message.getSendUser() + " " + message.getToUser())
                        || fileName.endsWith(message.getToUser() + " " + message.getSendUser())){
                    writeToFileMessage(fileName,message);
                    return false;
                }
            }
        }
        return true;
    }

    private void createAndAddToFile(Message message){
        file = new File(FILE_CATALOG_DATABASE_MESSAGES +"/"+ message.getSendUser() + " " + message.getToUser());
        file.mkdir();
        writeToFileMessage(file.getAbsolutePath(),message);
    }
    private void writeToFileMessage(String directory,Message message){
        file = new File(directory +"/"+ message.getSendUser() + "_" + message.getDate() + ".txt");
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(message.getContent());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
