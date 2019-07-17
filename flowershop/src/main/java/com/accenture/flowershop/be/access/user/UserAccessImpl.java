package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class UserAccessImpl implements UserAccess {

    private Map<String, User> users = new TreeMap<>();

    public EntityManager usersEntityManager = Persistence.createEntityManagerFactory("FLOWERSHOP").createEntityManager();

    public UserAccessImpl() {
    }

    public void saveUser(User user){
        usersEntityManager.getTransaction().begin();
        usersEntityManager.merge(user);
        usersEntityManager.getTransaction().commit();
    }

    public void delete(String login){
        usersEntityManager.getTransaction().begin();
        usersEntityManager.remove(get(login));
        usersEntityManager.getTransaction().commit();
    }

    public User get(String login){
        return usersEntityManager.find(User.class, login);
    }

    public void update(User user){
        usersEntityManager.getTransaction().begin();
        usersEntityManager.merge(user);
        usersEntityManager.getTransaction().commit();
    }

    public Map<String, User> getAll(){
        TypedQuery<User> namedQuery = usersEntityManager.createNamedQuery("User.getAll", User.class);
        for (User list: namedQuery.getResultList()){
            users.put(list.getLogin(),list);
        }
        return users;
    }
}