package file.system.based.chat.repository.impl;

import file.system.based.chat.model.User;
import file.system.based.chat.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    public static final String NAME_CATALOG_DATABASE = "database";
    public static final String FILE_CATALOG_DATABASE_USERS = "database/users";
    private File file;
    private Map<String, User> map = new HashMap<>();

    @PostConstruct
    public void init(){
        file = new File(NAME_CATALOG_DATABASE);
        if (!Files.exists(Paths.get(NAME_CATALOG_DATABASE))) {
            file.mkdir();
            file = new File(FILE_CATALOG_DATABASE_USERS);
            file.mkdir();
        } else if (!Files.exists(Paths.get(FILE_CATALOG_DATABASE_USERS))) {
            file = new File(FILE_CATALOG_DATABASE_USERS);
            file.mkdir();
        } else {
            file = new File(FILE_CATALOG_DATABASE_USERS);
        }

    }

    @Override
    public Optional<User> findByLogin(String login){
        loadMap();
        return Optional.of(map.get(login + ".txt"));
    }

    public List<User> findAll() {
        loadMap();
        return new ArrayList<>(map.values());
    }

    @Override
    public void save(User user) {
        loadMap();
        file = new File(FILE_CATALOG_DATABASE_USERS +"\\"+ user.getLogin() + ".txt");
        FileWriter fileWriter;
        try {
            if (file.createNewFile()){
                fileWriter = new FileWriter(file);
                System.out.println(file.getAbsolutePath());
                fileWriter.write(autoId() + "\n" + user.getLogin() + "\n" + user.getPassword());
                fileWriter.flush();
                fileWriter.close();
            }
            else{
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap() {
        File findFile = new File(FILE_CATALOG_DATABASE_USERS);
        try {
            String[] files = findFile.list((folder, name) -> name.endsWith(".txt"));
            if (files != null) {
                for (String fileName : files) {

                    map.put(fileName, readeFile(fileName));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User readeFile(String ss) throws IOException {
        File test = new File(FILE_CATALOG_DATABASE_USERS + "/" + ss);
        User user = new User();
        FileReader fr = new FileReader(test);
        BufferedReader reader = new BufferedReader(fr);
        String id;
        String password;
        String login;
        id = reader.readLine();
        user.setId(Integer.parseInt(id));

        login = reader.readLine();
        user.setLogin(login);

        password = reader.readLine();
        user.setPassword(password);
        reader.close();
        fr.close();
        return user;
    }

    private long autoId(){
        long maxElement = 0;
        for (Map.Entry<String, User> entry : map.entrySet()) {
            if(maxElement<entry.getValue().getId()){
                maxElement = entry.getValue().getId();
            }
        }
        return maxElement+1;
    }
}
