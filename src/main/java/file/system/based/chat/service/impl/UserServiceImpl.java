package file.system.based.chat.service.impl;

import file.system.based.chat.model.User;
import file.system.based.chat.repository.UserRepository;
import file.system.based.chat.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

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
        userRepository.save(user);
    }

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String s) {

        UserDetails details = findByLogin(s).orElse(null);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    details, null, details.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return details;
    }


}
