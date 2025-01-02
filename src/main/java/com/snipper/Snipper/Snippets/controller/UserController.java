package com.snipper.Snipper.Snippets.controller;


import com.snipper.Snipper.Snippets.AuthRequest;
import com.snipper.Snipper.Snippets.entity.Snippets;
import com.snipper.Snipper.Snippets.entity.User;
import com.snipper.Snipper.Snippets.service.UserService;
import com.snipper.Snipper.Snippets.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {
    //added Autowired here
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createuser(@RequestBody User user){
        return userService.createUser(user);
    }

    //added
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile(){
        return "Welcome to the user profile.";
    }

    @PostMapping("/generateToken")
    public Map<String, String> generateToken(@RequestBody AuthRequest authRequest) {
        return Map.of("token", jwtUtil.generateToken(authRequest.getEmail()));
    }
//    public String authenticateAndGenerateToken(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//        );
//
//        if(authentication.isAuthenticated()){
//            return jwtUtil.generateToken(authRequest.getUsername());
//        } else {
//            throw new UsernameNotFoundException("Invalid user request!");
//        }
//    }

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
