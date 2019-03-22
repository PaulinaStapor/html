package p.stapor.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import p.stapor.userservice.RegisterForm;
import p.stapor.userservice.controller.exception.UserNotExistException;
import p.stapor.userservice.repository.UserRepository;
import p.stapor.userservice.service.UserRegistrationService;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private UserRegistrationService userRegistrationService;
    private UserRepository userRepository;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService, UserRepository userRepository) {
        this.userRegistrationService = userRegistrationService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/register")
    public String registration(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "registrationForm";
    }

    @PostMapping(value = "/register")
    public String registration(@ModelAttribute @Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        try {
            userRegistrationService.registerUser(registerForm);
        } catch (UserNotExistException e) {
            bindingResult.rejectValue("userEmail", "userEntity.exists", "User already exists.");
            return "registrationForm";

        }
        return "registerResult";
    }
}
