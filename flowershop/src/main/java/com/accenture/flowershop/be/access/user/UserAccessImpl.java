package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class UserAccessImpl implements UserAccess {

    private Map<String, User> users = new TreeMap<>();

    @PersistenceContext
    private EntityManager entityManager;

    public UserAccessImpl() {
    }

    public void saveUser(User user){
        entityManager.merge(user);
    }

    public void delete(String login){
        entityManager.remove(get(login));
    }

    public User get(String login){
        return entityManager.find(User.class, login);
    }

    public void update(User user){
        entityManager.merge(user);
    }

    public Map<String, User> getAll(){
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getAll", User.class);
        for (User list: namedQuery.getResultList()){
            users.put(list.getLogin(),list);
        }
        return users;
    }
}