package file.system.based.chat.service;

import file.system.based.chat.model.User;
import file.system.based.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByLogin(String login) throws IOException;

    List<User> findAll() throws IOException;

    void save(User user);
}
