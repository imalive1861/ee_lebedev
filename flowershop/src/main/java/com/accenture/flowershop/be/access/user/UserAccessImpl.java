package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class UserAccessImpl implements UserAccess {

    private Map<String, User> users = new TreeMap<>();

    public EntityManager usersEntityManager = Persistence.createEntityManagerFactory("FLOWERSHOP").createEntityManager();

    public UserAccessImpl() {
    }

    public void saveUser(String login, String password, String name, String address,
                         String phoneNumber, BigDecimal score, int sale, String role){
        User user = new User(login, password, name, address,
                phoneNumber ,score, sale, role);
        usersEntityManager.getTransaction().begin();
        usersEntityManager.merge(user);
        usersEntityManager.getTransaction().commit();
    }

    public void delete(long id){
        usersEntityManager.getTransaction().begin();
        usersEntityManager.remove(get(id));
        usersEntityManager.getTransaction().commit();
    }

    public User get(long id){
        return usersEntityManager.find(User.class, id);
    }

    public void update(User user){
        usersEntityManager.getTransaction().begin();
        usersEntityManager.merge(user);
        usersEntityManager.getTransaction().commit();
    }

    public Map<String, User> getAllUsers(){
        TypedQuery<User> namedQuery = usersEntityManager.createNamedQuery("User.getAll", User.class);
        for (User list: namedQuery.getResultList()){
            users.put(list.getLogin(),list);
        }
        return users;
    }
}