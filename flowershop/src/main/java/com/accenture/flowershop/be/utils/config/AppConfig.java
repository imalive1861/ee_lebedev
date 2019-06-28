package com.accenture.flowershop.be.utils.config;

import com.accenture.flowershop.be.access.user.UserAccess;
import com.accenture.flowershop.be.access.user.UserAccessImpl;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserAccess userAccess(){
        return new UserAccessImpl();
    }
    @Bean
    public UserBusinessService userBusinessService(UserAccess userAccess){
        return new UserBusinessServiceImpl(userAccess);
    }
}
