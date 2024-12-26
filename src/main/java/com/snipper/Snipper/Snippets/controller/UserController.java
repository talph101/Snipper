package com.snipper.Snipper.Snippets.controller;


import com.snipper.Snipper.Snippets.entity.Snippets;
import com.snipper.Snipper.Snippets.entity.User;
import com.snipper.Snipper.Snippets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    //added Autowired here
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

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
    public String authenticateAndGenerateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
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
