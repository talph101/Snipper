package com.snipper.Snipper.Snippets.service.impl;

import com.snipper.Snipper.Snippets.entity.User;
import com.snipper.Snipper.Snippets.repository.UserRepository;
import com.snipper.Snipper.Snippets.service.UserService;
import com.snipper.Snipper.Snippets.util.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl() {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        //Hash the password
        String hashedPassword = BCryptUtil.generatedSecurePassword(user.getPassword());
        user.setPassword(hashedPassword);
//        user.setRoles("ROLE_USER");
        return userRepository.save(user);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User userById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
        return userToDelete;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRoles()))
        );
    }
}
