package com.accenture.flowershop.shop.be.utils.config.spring;

import com.accenture.flowershop.shop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.shop.be.service.business.flower.FlowerBusinessServiceImpl;
import com.accenture.flowershop.shop.be.service.marshgalling.user.UserMarshallingService;
import com.accenture.flowershop.shop.be.service.marshgalling.user.UserMarshallingServiceImpl;
import com.accenture.flowershop.shop.be.utils.marshalling.XMLConverter;
import com.accenture.flowershop.shop.services.jms.Cons;
import com.accenture.flowershop.shop.services.jms.Prod;
import com.accenture.flowershop.shop.services.jms.ProducerTest;
import com.accenture.flowershop.shop.services.ws.FlowersStockWebServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.oxm.castor.CastorMarshaller;

import javax.xml.ws.Endpoint;

/**
 * Конфигурационный класс для инициализации дополнительных модулей.
 */
@Configuration
@PropertySource(value = "classpath:config/config.properties")
@EnableJpaRepositories("com.accenture.flowershop.shop.be.repository")
public class AppConfig {

    @Value("${filepath.user}")
    private String userXML;

    @Bean
    public ProducerTest producerTest() {
        return new ProducerTest(userMarshallingService(), prod(), cons());
    }

    @Bean
    public Prod prod() {
        return new Prod();
    }

    @Bean
    public Cons cons() {
        return new Cons();
    }

    @Bean
    public UserMarshallingService userMarshallingService() {
        UserMarshallingService ums = new UserMarshallingServiceImpl(xmlConverter());
        ums.setUserXmlPath(userXML);
        return ums;
    }

    @Bean
    public XMLConverter xmlConverter() {
        XMLConverter converter = new XMLConverter();
        converter.setMarshaller(castorMarshaller());
        converter.setUnmarshaller(castorMarshaller());
        return converter;
    }

    @Bean
    public CastorMarshaller castorMarshaller() {
        CastorMarshaller castorMarshaller = new CastorMarshaller();
        Resource resource = new ClassPathResource("config/mapping.xml");
        castorMarshaller.setMappingLocation(resource);
        return castorMarshaller;
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("com.accenture.flowershop.shop");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public FlowerBusinessService flowerBusinessService() {
        return new FlowerBusinessServiceImpl();
    }

    @Bean
    public Endpoint app() {
        EndpointImpl endpoint =
                new EndpointImpl(springBus(),
                        new FlowersStockWebServiceImpl(flowerBusinessService()));
        endpoint.publish("/flowers");
        return endpoint;
    }
}
