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
    private File file = new File(NAME_CATALOG_DATABASE);
    private Map<String, User> map = new HashMap<>();

    @PostConstruct
    public void init(){
        file.mkdir();
        new File(FILE_CATALOG_DATABASE_USERS).mkdir();
    }

    @Override
    public Optional<User> findByLogin(String login){
        File findFile = new File(FILE_CATALOG_DATABASE_USERS);
        String[] files = findFile.list((folder, name) -> name.endsWith(".txt"));
        if (files != null) {
            Arrays.stream(files).findFirst();
            for (String fileName : files) {
                if((login + ".txt").equals(fileName)){
                   return Optional.of(readeFile(fileName));
                }
            }
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        loadMap();
        return new ArrayList<>(map.values());
    }

    @Override
    public void save(User user) {
        file = new File(FILE_CATALOG_DATABASE_USERS +"\\"+ user.getLogin() + ".txt");
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(autoId() + "\n" + user.getLogin() + "\n" + user.getPassword());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap() {
        File findFile = new File(FILE_CATALOG_DATABASE_USERS);
        String[] files = findFile.list((folder, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (String fileName : files) {
                map.put(fileName, readeFile(fileName));
            }
        }
    }

    private User readeFile(String nameReadFile) {
        String id;
        String password;
        String login;
        User user = new User();
        File test = new File(FILE_CATALOG_DATABASE_USERS + "/" + nameReadFile);
        try( FileReader fr = new FileReader(test);BufferedReader reader = new BufferedReader(fr)) {
            id = reader.readLine();
            user.setId(Integer.parseInt(id));

            login = reader.readLine();
            user.setLogin(login);

            password = reader.readLine();
            user.setPassword(password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    private long autoId(){
        loadMap();
        //return map.values().stream().max(User::compare)+1;
   return 1;
    }
}
