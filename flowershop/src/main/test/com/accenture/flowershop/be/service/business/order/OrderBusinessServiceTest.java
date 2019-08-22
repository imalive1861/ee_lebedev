package com.accenture.flowershop.be.service.business.order;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.utils.config.spring.AppConfig;
import com.accenture.flowershop.be.utils.config.spring.ApplicationConfig;
import com.accenture.flowershop.be.utils.config.spring.MVCConfig;
import com.accenture.flowershop.be.utils.config.spring.WebConfig;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {WebConfig.class, ApplicationConfig.class, MVCConfig.class, AppConfig.class})
@WebAppConfiguration
@Transactional
public class OrderBusinessServiceTest {

    @Autowired
    private OrderBusinessService orderBusinessService;

    @Test
    public void close_orderWithPaidStatus_orderWithClosedStatusReturned(){
        Order order = orderBusinessService.get(2L);
        orderBusinessService.close(order);
        assertEquals("Check order with \"PAID\" status failed!",
                order.getStatus(), OrderStatus.CLOSED);
    }

    @Test
    public void close_orderWithOpenedStatus_orderWithOpenedStatusReturned(){
        Order order = orderBusinessService.get(1L);
        orderBusinessService.close(order);
        assertNotEquals("Check order with \"OPENED\" status failed!",
                order.getStatus(), OrderStatus.CLOSED);
    }

    @Test
    public void close_orderWithClosedStatus_orderWithClosedReturned(){
        Order order = orderBusinessService.get(3L);
        orderBusinessService.close(order);
        assertEquals("Check order with \"CLOSED\" status failed!",
                order.getStatus(), OrderStatus.CLOSED);
    }
}