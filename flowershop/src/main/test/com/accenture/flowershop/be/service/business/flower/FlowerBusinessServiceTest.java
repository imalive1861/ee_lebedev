package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.utils.config.spring.AppConfig;
import com.accenture.flowershop.be.utils.config.spring.ApplicationConfig;
import com.accenture.flowershop.be.utils.config.spring.MVCConfig;
import com.accenture.flowershop.be.utils.config.spring.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {WebConfig.class, ApplicationConfig.class, MVCConfig.class, AppConfig.class})
@WebAppConfiguration
@Transactional
public class FlowerBusinessServiceTest {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Test
    public void update_333_flowerWith333NumberReturned(){
        Flower flower = new Flower.Builder()
                .name("Test")
                .number(333)
                .price(new BigDecimal(1))
                .build();
        flowerBusinessService.update(flower);
        assertEquals(flowerBusinessService.get(flower.getId()).getNumber(),333);
    }

    /*@Test
    public void getFlowerByName_Rose_flowerWithRoseNameReturned(){
        List<Flower> flower = flowerBusinessService.getFlowerByName("Rose");
        assertEquals(flower.get(0).getName(),"Rose");
    }

    @Test
    public void getFlowerByName_randomName_emptyListReturned(){
        List<Flower> flower = flowerBusinessService.getFlowerByName("lagoigiogjaoig");
        assertTrue(flower.isEmpty());
    }

    @Test
    public void getFlowerByName_emptyName_allFlowerReturned(){
        List<Flower> flower = flowerBusinessService.getFlowerByName("");
        assertEquals(flower.size(), flowerBusinessService.getAll().size());
    }

    @Test
    public void getFlowerByPrice_min123Max157_flowerWithPriceBetween123And157Returned(){
        List<Flower> flower = flowerBusinessService.getFlowerByPrice("123", "157");
        assertEquals(flower.size(),3);
    }

    @Test
    public void getFlowerByPrice_min123MaxEmpty_flowerWithPriceMoreThan123Returned(){
        List<Flower> flower = flowerBusinessService.getFlowerByPrice("123", "");
        assertEquals(flower.size(),8);
    }

    @Test
    public void getFlowerByPrice_mixEmptyMax157_flowerLessThan157Returned(){
        List<Flower> flower = flowerBusinessService.getFlowerByPrice("", "157");
        assertEquals(flower.size(),5);
    }

    @Test
    public void getFlowerByPrice_mixEmptyMaxEmpty_allFlowerReturned(){
        List<Flower> flower = flowerBusinessService.getFlowerByPrice("", "");
        assertEquals(flower.size(), flowerBusinessService.getAll().size());
    }*/
}
