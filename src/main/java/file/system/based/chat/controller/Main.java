package file.system.based.chat.controller;

import file.system.based.chat.model.User;
import file.system.based.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Main {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String homePage(Authentication authentication) {
        if(authentication!=null){

            return "redirect:/";
        }
        return "home";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPage(User user) {
        userService.save(user);
        return "redirect:/login";
    }
}
