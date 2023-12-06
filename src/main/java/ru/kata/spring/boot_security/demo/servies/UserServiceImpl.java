package ru.kata.spring.boot_security.demo.servies;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void save(ArrayList<Integer> roles, String name, String password, String email) {
        Set<Role> roleSet = new HashSet<>();
        for (Integer roleId : roles) {
            roleSet.add(new Role(roleId));
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(password));
        userDao.save(user);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional
    @Override
    public void update(ArrayList<Integer> roles, String name, String password, String email) {

        User user = userDao.getUserByName(name);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roleSet1 = new HashSet<>();
        for (Integer id : roles) {
            roleSet1.add(roleDao.getById(id));
        }
        user.setRoles(roleSet1);
        userDao.update(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User getUserByNamePass(String name, String password) {
        return userDao.getUserByNamePass(name,password);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
