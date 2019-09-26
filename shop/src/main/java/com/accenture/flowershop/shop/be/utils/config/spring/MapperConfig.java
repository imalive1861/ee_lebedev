package com.accenture.flowershop.shop.be.utils.config.spring;

import com.accenture.flowershop.shop.be.entity.Cart;
import com.accenture.flowershop.shop.be.entity.Flower;
import com.accenture.flowershop.shop.be.entity.Order;
import com.accenture.flowershop.shop.be.entity.User;
import com.accenture.flowershop.shop.fe.dto.CartDTO;
import com.accenture.flowershop.shop.fe.dto.FlowerDTO;
import com.accenture.flowershop.shop.fe.dto.OrderDTO;
import com.accenture.flowershop.shop.fe.dto.UserDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для инициализации маппера.
 */
@Configuration
public class MapperConfig {
    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Cart.class, CartDTO.class);
                mapping(Flower.class, FlowerDTO.class);
                mapping(Order.class, OrderDTO.class);
                mapping(User.class, UserDTO.class);
            }
        };
    }

    @Bean
    public DozerBeanMapper beanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }
}
