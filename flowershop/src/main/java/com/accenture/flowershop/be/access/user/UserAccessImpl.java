package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserAccessImpl implements UserAccess {

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
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.login = ?1", User.class);
        query.setParameter(1, login);
        if (query.getResultList().isEmpty()){
            return null;
        }
        return query.getSingleResult();
    }

    public void update(User user){
        entityManager.merge(user);
    }

    public List<User> getAll(){
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }
}