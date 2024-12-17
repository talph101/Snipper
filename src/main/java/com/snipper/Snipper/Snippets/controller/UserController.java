package com.snipper.Snipper.Snippets.controller;


import com.snipper.Snipper.Snippets.entity.Snippets;
import com.snipper.Snipper.Snippets.entity.User;
import com.snipper.Snipper.Snippets.service.UserService;
import com.snipper.Snipper.Snippets.util.BCryptUtil;
import com.snipper.Snipper.Snippets.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createuser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest){
        User user = userService.findByEmail(loginRequest.getEmail());
        if (user == null || !BCryptUtil.matchPasswords(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        String token = JwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok().body("Bearer " + token);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.allUsers();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.userById(id);
    }

    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
