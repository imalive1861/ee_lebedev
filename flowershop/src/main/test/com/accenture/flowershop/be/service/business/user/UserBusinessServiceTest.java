package com.accenture.flowershop.be.service.business.user;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.utils.config.spring.AppConfig;
import com.accenture.flowershop.be.utils.config.spring.ApplicationConfig;
import com.accenture.flowershop.be.utils.config.spring.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.springframework.test.util.AssertionErrors.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {WebConfig.class, ApplicationConfig.class, AppConfig.class})
@WebAppConfiguration
public class UserBusinessServiceTest {

    @Autowired
    private UserBusinessService userBusinessService;

    @Test
    public void logIn_admin_adminUserReturned(){
        User admin = userBusinessService.logIn("admin", "admin123");
        assertNotNull("Login \"admin\" failed verification!", admin);
    }

    @Test
    public void logIn_randomLoginAndPassword_nullReturned(){
        User random = userBusinessService.logIn("dafsdfa","kasdjhfg");
        assertNull(random);
    }

    @Test
    public void logIn_emptyLoginAndRandomPassword_nullReturned(){
        User user = userBusinessService.logIn("","asdfsdf");
        assertNull(user);
    }

    @Test
    public void logIn_adminLoginAndEmptyPassword_nullReturned(){
        User user = userBusinessService.logIn("admin","");
        assertNull(user);
    }

    @Test
    public void logIn_adminLoginAndRandomPassword_nullReturned(){
        User user = userBusinessService.logIn("admin","asdfsdf");
        assertNull(user);
    }

    @Test
    public void logIn_emptyLoginAndPassword_nullReturned(){
        User user = userBusinessService.logIn("","");
        assertNull(user);
    }

    @Test
    public void existsByLogin_adminLogin_trueReturned(){
        boolean admin = userBusinessService.existsByLogin("admin");
        assertTrue(admin);
    }

    @Test
    public void existsByLogin_emptyLogin_falseReturned(){
        boolean empty = userBusinessService.existsByLogin("");
        assertFalse(empty);
    }

    @Test
    public void existsByLogin_randomLogin_falseReturned(){
        boolean random = userBusinessService.existsByLogin("dafsdfasfdfasdfasdfasfadbn");
        assertFalse(random);
    }

    @Test
    public void update_user1DiscountUpdate_user1DiscountReturned(){
        User user1 = userBusinessService.getByLogin("user1");
        user1.setDiscount(11);
        userBusinessService.update(user1);
        User user1New = userBusinessService.getByLogin("user1");
        assertEquals("User \"user1\" failed verification!",
                user1New.getDiscount(), user1.getDiscount());
    }

    @Test
    public void getByLogin_admin_adminUserReturned(){
        User admin = userBusinessService.getByLogin("admin");
        assertEquals("Login \"admin\" failed verification!",admin.getLogin(),"admin");
    }

    @Test
    public void getByLogin_randomLogin_nullReturned(){
        User user = userBusinessService.getByLogin("dafsdfasfdfasdfasdfasfadbn");
        assertNull(user);
    }

    @Test
    public void getByLogin_emptyLogin_nullReturned(){
        User user = userBusinessService.getByLogin("");
        assertNull(user);
    }
}
