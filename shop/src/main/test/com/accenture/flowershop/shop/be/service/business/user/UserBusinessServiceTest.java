package com.accenture.flowershop.shop.be.service.business.user;

import com.accenture.flowershop.shop.be.entity.User;
import com.accenture.flowershop.shop.be.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserBusinessServiceTest {

    @Mock
    UserBusinessService ubs;

    private User admin;

    @Before
    public void init() {
        admin = new User.Builder()
                .login("admin")
                .password("admin123")
                .build();
        when(ubs.logIn("admin", "admin123")).thenReturn(admin);
        when(ubs.existsByLogin("admin")).thenReturn(true);
        when(ubs.getByLogin("admin")).thenReturn(admin);

    }

    @Test
    public void logIn_admin_adminUserReturned(){
        assertEquals(ubs.logIn("admin", "admin123"), admin);
        verify(ubs).logIn("admin", "admin123");

    }

    @Test
    public void logIn_randomLoginAndPassword_nullReturned(){
        assertNull(ubs.logIn("dafsdfa", "kasdjhfg"));
        verify(ubs).logIn("dafsdfa","kasdjhfg");
    }

    @Test
    public void logIn_emptyLoginAndRandomPassword_nullReturned(){
        assertNull(ubs.logIn("", "asdfsdf"));
        verify(ubs).logIn("","asdfsdf");
    }

    @Test
    public void logIn_adminLoginAndEmptyPassword_nullReturned(){
        assertNull(ubs.logIn("admin", ""));
        verify(ubs).logIn("admin","");
    }

    @Test
    public void logIn_adminLoginAndRandomPassword_nullReturned(){
        assertNull(ubs.logIn("admin","asdfsdf"));
        verify(ubs).logIn("admin","asdfsdf");
    }

    @Test
    public void logIn_emptyLoginAndPassword_nullReturned(){
        assertNull(ubs.logIn("",""));
        verify(ubs).logIn("","");
    }

    @Test
    public void existsByLogin_adminLogin_trueReturned(){
        assertTrue(ubs.existsByLogin("admin"));
        verify(ubs).existsByLogin("admin");
    }

    @Test
    public void existsByLogin_emptyLogin_falseReturned(){
        assertFalse(ubs.existsByLogin(""));
        verify(ubs).existsByLogin("");
    }

    @Test
    public void existsByLogin_randomLogin_falseReturned(){
        assertFalse(ubs.existsByLogin("dafsdfasfdfasdfasdfasfadbn"));
        verify(ubs).existsByLogin("dafsdfasfdfasdfasdfasfadbn");
    }

    @Test
    public void getByLogin_admin_adminUserReturned(){
        assertEquals(ubs.getByLogin("admin"), admin);
        verify(ubs).getByLogin("admin");
    }

    @Test
    public void getByLogin_randomLogin_nullReturned(){
        assertNull(ubs.getByLogin("sdfafwe"));
        verify(ubs).getByLogin("sdfafwe");
    }

    @Test
    public void getByLogin_emptyLogin_nullReturned(){
        assertNull(ubs.getByLogin(""));
        verify(ubs).getByLogin("");
    }
}
