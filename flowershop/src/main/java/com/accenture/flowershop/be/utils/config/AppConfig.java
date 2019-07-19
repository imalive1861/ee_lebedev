package com.accenture.flowershop.be.utils.config;

import com.accenture.flowershop.be.access.card.CardAccess;
import com.accenture.flowershop.be.access.card.CardAccessImpl;
import com.accenture.flowershop.be.access.flower.FlowerAccess;
import com.accenture.flowershop.be.access.flower.FlowerAccessImpl;
import com.accenture.flowershop.be.access.order.OrderAccess;
import com.accenture.flowershop.be.access.order.OrderAccessImpl;
import com.accenture.flowershop.be.access.user.UserAccess;
import com.accenture.flowershop.be.access.user.UserAccessImpl;
import com.accenture.flowershop.be.service.business.card.CardBusinessService;
import com.accenture.flowershop.be.service.business.card.CardBusinessServiceImpl;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessServiceImpl;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessServiceImpl;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.service.business.user.UserBusinessServiceImpl;
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

    @Bean
    public FlowerAccess flowerAccess(){
        return new FlowerAccessImpl();
    }
    @Bean
    public FlowerBusinessService flowerBusinessService(FlowerAccess flowerAccess){
        return new FlowerBusinessServiceImpl(flowerAccess);
    }

    @Bean
    public OrderAccess orderAccess(){
        return new OrderAccessImpl();
    }
    @Bean
    public OrderBusinessService orderBusinessService(OrderAccess orderAccess){
        return new OrderBusinessServiceImpl(orderAccess);
    }

    @Bean
    public CardAccess cardAccess(){
        return new CardAccessImpl();
    }
    @Bean
    public CardBusinessService cardBusinessService(CardAccess cardAccess,
                                                    UserBusinessService userBusinessService,
                                                    OrderBusinessService orderBusinessService,
                                                    FlowerBusinessService flowerBusinessService){
        return new CardBusinessServiceImpl(cardAccess,
                userBusinessService, orderBusinessService, flowerBusinessService);
    }
}
