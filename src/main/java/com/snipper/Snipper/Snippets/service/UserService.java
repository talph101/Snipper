package com.snipper.Snipper.Snippets.service;

import com.snipper.Snipper.Snippets.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> allUsers();

    User userById(Long id);

    User deleteUser(Long id);
}
