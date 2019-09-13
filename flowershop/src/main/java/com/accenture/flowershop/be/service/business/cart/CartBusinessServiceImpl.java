package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.be.entity.Cart;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.repository.cart.CartRepository;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.fe.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.math.RoundingMode.UP;

/**
 * Реализация интерфейса CartBusinessService.
 */
@Service
public class CartBusinessServiceImpl implements CartBusinessService {

    /**
     * Ссылка на уровень доступа к базе данных для сущности Cart.
     */
    @Autowired
    private CartRepository cartRepository;
    /**
     *
     */
    @Autowired
    private UserBusinessService userBusinessService;
    /**
     * Карта временных заказов пользователей.
     */
    private Map<String, Order> cart = new HashMap<>();

    public CartBusinessServiceImpl() {
    }

    @Override
    public Cart get(Long id) {
        return cartRepository.getOne(id);
    }

    @Override
    public Map<String, Order> getCart() {
        return cart;
    }

    @Override
    public Order getCartById(String login) {
        if (cart.containsKey(login)) {
            return cart.get(login);
        }
        return null;
    }

    @Override
    public BigDecimal getSumPriceWithDiscount(BigDecimal price, int sale, int number) {
        BigDecimal sum = price.multiply(new BigDecimal(number));
        BigDecimal newSale = new BigDecimal(sale).setScale(2, UP);
        newSale = newSale.divide(new BigDecimal(100.00), UP).setScale(2, UP);
        newSale = newSale.multiply(sum).setScale(2, UP);
        sum = sum.subtract(newSale).setScale(2, UP);
        return sum;
    }

    @Override
    public void countAllSumPrice(Order order) {
        BigDecimal sumPriceWithoutDiscount = new BigDecimal(0.00);
        BigDecimal sumPriceWithDiscount = new BigDecimal(0.00);
        Set<Cart> carts = order.getCarts();
        if (!carts.isEmpty()) {
            for (Cart c : carts) {
                sumPriceWithoutDiscount = sumPriceWithoutDiscount.add(c.getSumPriceWithoutDiscount());
                sumPriceWithDiscount = sumPriceWithDiscount.add(c.getSumPriceWithDiscount());
            }
        }
        order.setSumPriceWithoutDiscount(sumPriceWithoutDiscount);
        order.setSumPriceWithDiscount(sumPriceWithDiscount);
    }

    @Override
    public boolean isAddFlowerToCart(String login, Flower flower, int number) {
        if (number <= 0) {
            return false;
        }
        Order order = getCartById(login);
        Cart cart = null;
        for (Cart c : order.getCarts()) {
            if (c.getFlower().getId().equals(flower.getId())) {
                cart = c;
                break;
            }
        }
        User user = userBusinessService.getByLogin(login);
        BigDecimal sumPriceWithDiscount = getSumPriceWithDiscount(
                flower.getPrice(),
                user.getDiscount(),
                number);
        BigDecimal sumPriceWithoutDiscount = getSumPriceWithDiscount(
                flower.getPrice(),
                0,
                number);
        if (cart == null) {
            cart = new Cart.Builder()
                    .order(order)
                    .flower(flower)
                    .number(0)
                    .sumPriceWithoutDiscount(new BigDecimal(0.00))
                    .sumPriceWithDiscount(new BigDecimal(0.00))
                    .build();
            this.cart.get(login).getCarts().add(cart);
        }
        flower = cart.getFlower();
        int i = flower.getNumber() - (cart.getNumber() + number);
        if (i < 0) {
            return false;
        }
        cart.setNumber(cart.getNumber() + number);
        cart.setSumPriceWithoutDiscount(cart.getSumPriceWithoutDiscount().add(sumPriceWithoutDiscount));
        cart.setSumPriceWithDiscount(cart.getSumPriceWithDiscount().add(sumPriceWithDiscount));
        countAllSumPrice(order);
        user.getOrders().add(order);
        return true;
    }

    @Override
    public void setCart(String login) {
        cart.putIfAbsent(login, new Order.Builder()
                .status(OrderStatus.OPENED)
                .carts(new HashSet<>())
                .build());
        cart.get(login);
    }

    @Override
    public void clear(String login) {
        Order order = new Order.Builder()
                .status(OrderStatus.OPENED)
                .carts(new HashSet<>())
                .build();
        cart.put(login, order);
    }

    @Override
    public void deleteFlowerFromCart(Long flowerId, String login) {
        Order order = getCartById(login);
        Set<Cart> carts = order.getCarts();
        for (Cart c : carts) {
            if (c.getFlower().getId().equals(flowerId)) {
                carts.remove(c);
                break;
            }
        }
        countAllSumPrice(order);
    }
}