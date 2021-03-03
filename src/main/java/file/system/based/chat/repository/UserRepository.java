package file.system.based.chat.repository;

import file.system.based.chat.model.User;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByLogin(String login) throws IOException;

    List<User> findAll() throws IOException;

    void save(User user);
}
