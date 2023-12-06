package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    List<User> allUsers();
    void save(User user);
    void update(User user);
    void  delete(Long userId);
    User getUserById(Long id);
    User getUserByNamePass(String name, String password);
    User getUserByName(String name);
}
