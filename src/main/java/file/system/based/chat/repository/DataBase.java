package file.system.based.chat.repository;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DataBase {
    private File file = new File("database");
    DataBase(){
        file.mkdir();
    }
}
