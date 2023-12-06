package ru.kata.spring.boot_security.demo.servies;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> allRoles();

    Role getById(Integer id);

    Role getByName(String name);
}
