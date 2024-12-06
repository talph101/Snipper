package com.snipper.Snipper.Snippets.service.impl;

import com.snipper.Snipper.Snippets.entity.User;
import com.snipper.Snipper.Snippets.repository.UserRepository;
import com.snipper.Snipper.Snippets.service.UserService;
import com.snipper.Snipper.Snippets.util.BCryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        //Hash the password
        String hashedPassword = BCryptUtil.generatedSecurePassword(user.getPassword());
        user.setPassword(hashedPassword);
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
}
