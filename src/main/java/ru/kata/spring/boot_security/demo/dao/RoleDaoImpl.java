package ru.kata.spring.boot_security.demo.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("FROM Role", Role.class)
                .getResultList();
    }

    @Override
    public Role getById(Integer id) {
        Role role = entityManager.find(Role.class, id);
        return role;

    }

    @Override
    public Role getByName(String name) {
        return  (Role) entityManager.createQuery("FROM Role where name =: name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
