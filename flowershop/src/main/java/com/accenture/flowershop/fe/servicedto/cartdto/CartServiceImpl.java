package com.accenture.flowershop.fe.servicedto.cartdto;

import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
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

    public boolean isAddFlowerToCart(UserDTO userDTO, FlowerDTO flowerDTO, int number){
        if (number <= 0) {
            return false;
        }
        OrderDTO orderDTO = getCartById(userDTO.getLogin());
        CartDTO cartDTO = null;
        for (CartDTO c: orderDTO.getCarts()) {
            if (c.getFlower().getId() == flowerDTO.getId()) {
                cartDTO = c;
            }
        }
        BigDecimal sumPrice = getSumPriceWithDiscount(flowerDTO.getPrice(),userDTO.getDiscount(),number);
        if (cartDTO == null) {
            cartDTO = new CartDTO.Builder()
                    .order(orderDTO)
                    .flower(flowerDTO)
                    .number(0)
                    .sumPrice(new BigDecimal(0.00))
                    .build();
            this.cart.get(userDTO.getLogin()).getCarts().add(cartDTO);
        }
        flowerDTO = cartDTO.getFlower();
        int i = flowerDTO.getNumber() - number;
        if (i < 0) {
            return false;
        }
        cartDTO.setNumber(cartDTO.getNumber() + number);
        flowerDTO.setNumber(i);
        cartDTO.setSumPrice(cartDTO.getSumPrice().add(sumPrice));
        orderDTO.setSumPrice(getAllSumPrice(userDTO.getDiscount(), userDTO.getLogin()));
        return true;
    }

    private BigDecimal getSumPriceWithDiscount(BigDecimal price, int sale, int number) {
        BigDecimal sum = price.multiply(new BigDecimal(number));
        BigDecimal newSale = new BigDecimal(sale).setScale(2, UP);
        newSale = newSale.divide(new BigDecimal(100.00), UP).setScale(2, UP);
        newSale = newSale.multiply(sum).setScale(2, UP);
        sum = sum.subtract(newSale).setScale(2, UP);
        return sum;
    }

    public OrderDTO clear(String login){
        OrderDTO orderDTO = new OrderDTO.Builder()
                .status(OrderStatus.OPENED)
                .build();
        cart.put(login, orderDTO);
        return orderDTO;
    }

    private BigDecimal getAllSumPrice(int sale, String login){
        BigDecimal sum = new BigDecimal(0.00);
        List<CartDTO> carts = cart.get(login).getCarts();
        if (!carts.isEmpty()) {
            for (CartDTO c : carts) {
                sum = sum.add(c.getSumPrice());
            }
        }
        return sum;
    }
}
