package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.utils.EntityManagerUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class UserAccessImpl implements UserAccess {

    private Map<String, User> users = new TreeMap<>();

    private EntityManager entityManager = EntityManagerUtils.getEntityManager();

    public UserAccessImpl() {
    }

    public void saveUser(User user){
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public void delete(String login){
        entityManager.getTransaction().begin();
        entityManager.remove(get(login));
        entityManager.getTransaction().commit();
    }

    public User get(String login){
        return entityManager.find(User.class, login);
    }

    public void update(User user){
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public Map<String, User> getAll(){
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getAll", User.class);
        for (User list: namedQuery.getResultList()){
            users.put(list.getLogin(),list);
        }
        return users;
    }
}