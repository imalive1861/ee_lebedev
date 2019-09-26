package com.accenture.flowershop.shop.be.utils.config.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурационный класс для инициализации контекста веб-приложения.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.accenture.flowershop.shop")
@PropertySource(value = "classpath:db/migration/datasource.properties")
@EnableScheduling
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${db.driver}")
    private String PROP_DATABASE_DRIVER;
    @Value("${db.username}")
    private String PROP_DATABASE_USERNAME;
    @Value("${db.password}")
    private String PROP_DATABASE_PASSWORD;
    @Value("${db.url}")
    private String PROP_DATABASE_URL;
    @Value("${db.dialect}")
    private String PROP_DATABASE_DIALECT;
    @Value("${db.hbm2ddl.auto}")
    private String PROP_DATABASE_HBM2DDL;
    @Value("${db.show_sql}")
    private String PROP_DATABASE_SHOW_SQL;
    @Value("${db.format_sql}")
    private String PROP_DATABASE_FORMAT_SQL;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(PROP_DATABASE_DRIVER);
        dataSource.setUrl(PROP_DATABASE_URL);
        dataSource.setUsername(PROP_DATABASE_USERNAME);
        dataSource.setPassword(PROP_DATABASE_PASSWORD);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.accenture.flowershop.shop");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", PROP_DATABASE_DIALECT);
        properties.setProperty("hibernate.hbm2ddl.auto", PROP_DATABASE_HBM2DDL);
        properties.setProperty("show_sql", PROP_DATABASE_SHOW_SQL);
        properties.setProperty("hibernate.format_sql", PROP_DATABASE_FORMAT_SQL);
        properties.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        properties.setProperty("javax.persistence.lock.scope", "EXTENDED");
        entityManagerFactory.setJpaProperties(properties);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());
        tm.setDataSource(dataSource());
        return tm;
    }
}
