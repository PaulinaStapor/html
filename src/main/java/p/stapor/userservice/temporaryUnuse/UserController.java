//package p.stapor.userservice.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.util.MimeTypeUtils;
//import org.springframework.web.bind.annotation.*;
//import p.stapor.userservice.controller.dto.UserDto;
//import p.stapor.userservice.model.UserEntity;
//import p.stapor.userservice.service.UserService;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping(value = "/index")
//    public String homePage() {
//        return "index";
//    }
//
//    @GetMapping(value = "/users")
//    public String allUsers(Model model) {
//        model.addAttribute("usersEntityList", userService.getAllUsers());
//        return "users";
//    }
//
//    @RequestMapping(value = "/user/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//    public ResponseEntity<UserEntity> findUserById(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.getUserById(id));
//
//    }
//
//    @RequestMapping(value = "/users/{lastName}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//    public ResponseEntity<List<UserEntity>> findUserByLastName(@PathVariable String lastName) {
//        return ResponseEntity.ok(userService.getUsersByLastName(lastName));
//    }
//
//    @RequestMapping(value = "/users", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
//    public ResponseEntity addUser(@RequestBody @Valid UserDto userDTO) {
//        userService.addUser(userDTO);
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/users/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
//    public ResponseEntity byeByeUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/users/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
//    public ResponseEntity modify(@PathVariable Long id, @RequestBody @Valid UserDto userDTO) {
//        userService.modifyUser(id, userDTO);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//
//    }
//}
