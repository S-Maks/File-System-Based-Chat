package file.system.based.chat.controller;

import file.system.based.chat.model.User;
import file.system.based.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class Main {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage(Authentication authentication) throws IOException {
        System.out.println(userService.findAll());
        return "home";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPage(User user){
        userService.save(user);
        return "redirect:/";
    }
}
