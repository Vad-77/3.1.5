package ru.kata.spring.boot_security.demo.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void  delete(Long userId) {
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByNamePass(String name, String password) {
        return (User) entityManager.createQuery("FROM User where name =: name and password =: password")
                .setParameter("name", name)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        return  (User) entityManager.createQuery("FROM User where name =: name")
                .setParameter("name", name)
                .getSingleResult();
    }



    public List<User> allUsers() {
        return entityManager.createQuery("FROM User", User.class)
                .getResultList();
    }
}
