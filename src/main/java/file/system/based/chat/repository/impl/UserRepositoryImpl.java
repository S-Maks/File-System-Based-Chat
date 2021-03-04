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
            return Optional.of(readeFile(Arrays.stream(files).parallel().filter(s -> s.equals(login + ".txt")).findFirst().get()));
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        loadMap();
        return new ArrayList<>(map.values());
    }

    @Override
    public void save(User user) {
        String dataUser = autoId() +"\n" + user.getLogin() + "\n" + user.getPassword();
        file = new File(FILE_CATALOG_DATABASE_USERS +"/"+ user.getLogin() + ".txt");
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(dataUser);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap() {
        File findFile = new File(FILE_CATALOG_DATABASE_USERS);
        String[] files = findFile.list((folder, name) -> name.endsWith(".txt"));
        if (files != null) {
            map = Arrays.stream(files).parallel().collect(Collectors.toMap(s -> s, this::readeFile));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    private String autoId(){
        loadMap();
        if(!map.isEmpty()){
            return String.valueOf(map.values().stream().max(Comparator.comparingLong(User::getId)).get().getId()+1);
        }else{
            return "1";
        }
    }
}
