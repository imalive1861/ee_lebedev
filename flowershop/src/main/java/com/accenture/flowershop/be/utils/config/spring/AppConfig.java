package com.accenture.flowershop.be.utils.config.spring;

import com.accenture.flowershop.be.service.marshgalling.user.UserMarshallingService;
import com.accenture.flowershop.be.service.marshgalling.user.UserMarshallingServiceImpl;
import com.accenture.flowershop.be.utils.marshalling.XMLConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.castor.CastorMarshaller;

@Configuration
@PropertySource("classpath:config/config.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    private static final String userXML = "filepath.user";

    @Bean
    public UserMarshallingService userMarshallingService() {
        UserMarshallingService ums = new UserMarshallingServiceImpl(xmlConverter());
        ums.setUserXML(env.getProperty(userXML));
        return ums;
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
    public Logger logger(){
        return LoggerFactory.getLogger("com.accenture.flowershop");
    }
}
