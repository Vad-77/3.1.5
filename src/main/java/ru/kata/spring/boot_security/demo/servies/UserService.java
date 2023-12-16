package ru.kata.spring.boot_security.demo.servies;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.List;


public interface UserService {


    void save(ArrayList<Integer> roles, String name, String password, String email);
    User getUserById(Long id);
    List<User> allUsers();
    void update(ArrayList<Integer> roles, String name, String password, String email);
    void delete(Long id);
    User getUserByNamePass(String name, String password);
    User getUserByName(String name);
    User getUserByEmail(String email);

}
