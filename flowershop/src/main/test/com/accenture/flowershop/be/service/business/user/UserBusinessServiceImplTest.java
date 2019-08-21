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

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.springframework.test.util.AssertionErrors.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {WebConfig.class, ApplicationConfig.class, AppConfig.class})
@WebAppConfiguration
public class UserBusinessServiceImplTest {

    @Autowired
    private UserBusinessService userBusinessService;

    @Test
    public void logIn_admin_adminUserReturned(){
        User admin = userBusinessService.getByLogin("admin");
        assertEquals("Login \"admin\" failed verification!",admin.getLogin(),"admin");
    }

    @Test
    public void logIn_randomLogin_nullReturned(){
        User ololo = userBusinessService.getByLogin("dafsdfasfdfasdfasdfasfadbn");
        assertNull(ololo);
    }

    @Test
    public void logIn_emptyLogin_nullReturned(){
        User ololo = userBusinessService.getByLogin("");
        assertNull(ololo);
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
}
