package file.system.based.chat.service.impl;

import file.system.based.chat.model.User;
import file.system.based.chat.repository.UserRepository;
import file.system.based.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByLogin(String login) throws IOException {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> findAll() throws IOException {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        try {
            return findByLogin(s).orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
