package nl.koenm.apitest.controller;

import nl.koenm.apitest.model.User;
import nl.koenm.apitest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Object> getAllUsers() {
        Iterable<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value="/{nr}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Object> getUserById(@PathVariable long nr){
        User user = userService.findUserById(nr);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value="/check-if-exists")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Object> searchUserByUsername(@RequestParam(name="username", defaultValue = "") String name) {
        return ResponseEntity.ok().body(userService.getUserByUsername(name));
    }

    @PostMapping(value ="")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.ok("User successfully created");
    }

    @DeleteMapping(value="/{nr}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Object> deleteUserById(@PathVariable long nr) {
        userService.deleteUserById(nr);
        return ResponseEntity.ok("User successfully deleted");
    }


}
