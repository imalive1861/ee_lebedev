package com.accenture.flowershop.shop.be.utils.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Конфигурационный класс для инициализации SprinMVC.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.accenture.flowershop.shop"})
public class MVCConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/view/");
        bean.setSuffix(".jsp");
        bean.setOrder(0);
        return bean;
    }
}
