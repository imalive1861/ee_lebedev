package com.accenture.flowershop.fe.service.dto.cartdto;

import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static java.math.RoundingMode.UP;

/**
 * Реализация интерфейса CartService.
 * Свойства: mapper, cart.
 */
@Service
public class CartServiceImpl implements CartService {

    /**
     * Карта временных заказов пользователей.
     */
    private Map<String, OrderDTO> cart = new TreeMap<>();

    public CartServiceImpl() {}

    /**
     * Получить текущий заказ пользователя.
     * @param login - логин пользователя
     * @return объект OrderDTO
     */
    private OrderDTO getCartById(String login){
        if (cart.get(login) != null) {
            return cart.get(login);
        }
        return null;
    }

    /**
     * Рассчет суммы позиции с учетом скидки пользователя.
     * @param price - сумма позиции
     * @param sale - скидка пользователя
     * @param number - количество заказанных цветов
     * @return сумма позиции с учетом скидки пользователя
     */
    private BigDecimal getSumPriceWithDiscount(BigDecimal price, int sale, int number) {
        BigDecimal sum = price.multiply(new BigDecimal(number));
        BigDecimal newSale = new BigDecimal(sale).setScale(2, UP);
        newSale = newSale.divide(new BigDecimal(100.00), UP).setScale(2, UP);
        newSale = newSale.multiply(sum).setScale(2, UP);
        sum = sum.subtract(newSale).setScale(2, UP);
        return sum;
    }

    /**
     * Рассчет суммы заказа пользователя.
     * @param login - логин пользователя
     * @return сумма заказа пользователя
     */
    private BigDecimal getAllSumPrice(String login){
        BigDecimal sum = new BigDecimal(0.00);
        List<CartDTO> carts = cart.get(login).getCarts();
        if (!carts.isEmpty()) {
            for (CartDTO c : carts) {
                sum = sum.add(c.getSumPrice());
            }
        }
        return sum;
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
        BigDecimal sumPrice =
                getSumPriceWithDiscount(flowerDTO.getPrice(),userDTO.getDiscount(),number);
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
        orderDTO.setSumPrice(getAllSumPrice(userDTO.getLogin()));
        return true;
    }

    public OrderDTO clear(String login){
        OrderDTO orderDTO = new OrderDTO.Builder()
                .status(OrderStatus.OPENED)
                .build();
        cart.put(login, orderDTO);
        return orderDTO;
    }

    public OrderDTO setCart(String login){
        cart.putIfAbsent(login, new OrderDTO.Builder()
                .status(OrderStatus.OPENED)
                .build());
        return cart.get(login);
    }
}
