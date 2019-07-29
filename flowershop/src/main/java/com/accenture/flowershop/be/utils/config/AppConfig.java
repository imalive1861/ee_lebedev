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
import com.accenture.flowershop.be.service.business.card.CardService;
import com.accenture.flowershop.be.service.business.card.CardServiceImpl;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessServiceImpl;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessServiceImpl;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.service.business.user.UserBusinessServiceImpl;
import com.accenture.flowershop.be.service.marshgalling.user.UserMarshallingService;
import com.accenture.flowershop.be.utils.marshalling.XMLConverter;
import com.accenture.flowershop.fe.dto.mappers.CardMapper;
import com.accenture.flowershop.fe.dto.mappers.FlowerMapper;
import com.accenture.flowershop.fe.dto.mappers.OrderMapper;
import com.accenture.flowershop.fe.dto.mappers.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.castor.CastorMarshaller;

@Configuration
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public XMLConverter xmlConverter(){
        XMLConverter converter = new XMLConverter();
        converter.setMarshaller(castorMarshaller());
        converter.setUnmarshaller(castorMarshaller());
        return converter;
    }

    @Bean
    public CastorMarshaller castorMarshaller() {
        CastorMarshaller castorMarshaller = new CastorMarshaller();
        Resource resource = new ClassPathResource("mapping.xml");
        castorMarshaller.setMappingLocation(resource);
        return castorMarshaller;
    }

    @Bean
    public UserAccess userAccess(){
        return new UserAccessImpl();
    }
    @Bean
    public UserBusinessService userBusinessService(UserAccess userAccess, UserMapper userMapper,
                                                   UserMarshallingService userMarshallingService){
        return new UserBusinessServiceImpl(userAccess, userMapper, userMarshallingService);
    }

    @Bean
    public FlowerAccess flowerAccess(){
        return new FlowerAccessImpl();
    }
    @Bean
    public FlowerBusinessService flowerBusinessService(FlowerAccess flowerAccess, FlowerMapper flowerMapper){
        return new FlowerBusinessServiceImpl(flowerAccess, flowerMapper);
    }

    @Bean
    public OrderAccess orderAccess(){
        return new OrderAccessImpl();
    }
    @Bean
    public OrderBusinessService orderBusinessService(OrderAccess orderAccess,
                                                    UserBusinessService userBusinessService,
                                                     OrderMapper orderMapper){
        return new OrderBusinessServiceImpl(orderAccess, userBusinessService, orderMapper);
    }

    @Bean
    public CardAccess cardAccess(){
        return new CardAccessImpl();
    }
    @Bean CardService cardService() {
        return new CardServiceImpl();
    }
    @Bean
    public CardBusinessService cardBusinessService(CardAccess cardAccess,
                                                   CardService cardService,
                                                   CardMapper cardMapper,
                                                   OrderBusinessService orderBusinessService,
                                                   FlowerBusinessService flowerBusinessService){
        return new CardBusinessServiceImpl(cardAccess,
                cardService,
                cardMapper,
                orderBusinessService,
                flowerBusinessService);
    }
}
