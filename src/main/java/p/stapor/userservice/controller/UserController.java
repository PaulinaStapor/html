package p.stapor.userservice.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import p.stapor.userservice.model.UserEntity;
import p.stapor.userservice.repository.UserRepository;

import java.util.List;


@Controller
public class UserController {
    private UserRepository userRepository;
private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = "/index")
    public String homePage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "loginForm";
    }



    @GetMapping(value = "/users")
    public String showUsers(Model model) {
        model.addAttribute("usersEntityList", userRepository.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/searchuser")
    public String showUsers (@RequestParam(required = false) String query, Model model){
        model.addAttribute("queryValue", query);
        List<UserEntity> users=userRepository.findUserEntitiesByLastNameLike(query);
        model.addAttribute("usersEntityList", users);
        return "users";
    }
}
