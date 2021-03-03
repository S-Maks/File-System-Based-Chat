package file.system.based.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "file.system.based.chat")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}