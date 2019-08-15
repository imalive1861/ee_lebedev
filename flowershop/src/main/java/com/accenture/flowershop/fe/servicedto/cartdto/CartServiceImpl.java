package com.accenture.flowershop.fe.servicedto.cartdto;

import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static java.math.RoundingMode.UP;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private Mapper mapper;

    private Map<String, OrderDTO> cart = new TreeMap<>();

    public CartServiceImpl(){
    }

    private OrderDTO getCartById(String login){
            if (cart.get(login) != null) {
                return cart.get(login);
            }
        return null;
    }

    public OrderDTO setCartFromSession(String login){
        if (!cart.containsKey(login)){
            cart.put(login, new OrderDTO.Builder()
                    .status(OrderStatus.OPENED)
                    .build());
        }
        return cart.get(login);
    }

    public boolean isAddFlowerToCart(String login, FlowerDTO flowerDTO, int number){
        if (number < 0) {
            return false;
        }
        int i = flowerDTO.getNumber() - number;
        if (i < 0) {
            return false;
        }
        OrderDTO orderDTO = getCartById(login);
        CartDTO cartDTO = null;
        for (CartDTO c: orderDTO.getCarts()) {
            if (c.getFlower().getId() == flowerDTO.getId()) {
                cartDTO = c;
            }
        }
        BigDecimal sumPrice = flowerDTO.getPrice().multiply(new BigDecimal(number));
        if (cartDTO == null) {
            cartDTO = new CartDTO.Builder()
                    .order(orderDTO)
                    .flower(flowerDTO)
                    .number(0)
                    .sumPrice(new BigDecimal(0.00))
                    .build();
            this.cart.get(login).getCarts().add(cartDTO);
        }
        flowerDTO = cartDTO.getFlower();
        cartDTO.setNumber(cartDTO.getNumber() + number);
        flowerDTO.setNumber(i);
        cartDTO.setSumPrice(cartDTO.getSumPrice().add(sumPrice));
        return true;
    }

    public void clear(String login){
        cart.put(login, new OrderDTO.Builder()
                .status(OrderStatus.OPENED)
                .build());
    }

    public BigDecimal getAllSumPrice(int sale, String login){
        BigDecimal sum = new BigDecimal(0.00);
        if (!cart.isEmpty()) {
            for (CartDTO c : cart.get(login).getCarts()) {
                sum = sum.add(c.getSumPrice());
            }
            BigDecimal newSale = new BigDecimal(sale).setScale(2, UP);
            newSale = newSale.divide(new BigDecimal(100.00), UP).setScale(2, UP);
            newSale = newSale.multiply(sum).setScale(2, UP);
            sum = sum.subtract(newSale).setScale(2, UP);
        }
        return sum;
    }
}
