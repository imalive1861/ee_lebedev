package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.utils.config.SecurityConfig;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
public class UserAccessImpl implements UserAccess {

    private Map<String, User> users = new TreeMap<>();

    public UserAccessImpl() {
        User admin = new User(setNextId(),"admin","admin123","",
                "","",0,0, SecurityConfig.ROLE_ADMIN);
        users.put(admin.getLogin(), admin);
        User user1 = new User(setNextId(),"user1","user123","Vasia",
                "ABC","8800553535",2000.00,3, SecurityConfig.ROLE_CUSTOMER);
        users.put("user1", user1);
    }

    private int setNextId(){
        int i = 0;
        for (User user: users.values()){
            if (user.getId() > i){
                i = user.getId();
            }
        }
        return i;
    }

    public void saveUser(String login, String password, String name, String address,
                         String phoneNumber, double score, int sale, String role){
        User user = new User(setNextId(), login, password, name, address,
                phoneNumber ,score, sale, role);
        users.put(user.getLogin(), user);
    }

    public Map<String, User> getAllUsers(){
        return users;
    }
}