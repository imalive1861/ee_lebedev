package com.accenture.flowershop.be.utils.config.spring;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Конфигурационный класс для инициализации контекста веб-приложения.
 */
@Configuration
@EnableMongoRepositories("com.accenture.flowershop.be.repository")
@ComponentScan("com.accenture.flowershop")
@PropertySource(value = "classpath:db/migration/datasource.properties")
@EnableScheduling
public class ApplicationConfig extends AbstractMongoConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${db.name}")
    private String PROP_DATABASE_NAME;//"flowershop"
    @Value("${db.host}")
    private String PROP_DATABASE_HOST;//"127.0.0.1"
    @Value("${db.port}")
    private int PROP_DATABASE_PORT;//27017

    @Override
    protected String getDatabaseName() {
        return PROP_DATABASE_NAME;
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(PROP_DATABASE_HOST, PROP_DATABASE_PORT);
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.accenture.flowershop";
    }
}
