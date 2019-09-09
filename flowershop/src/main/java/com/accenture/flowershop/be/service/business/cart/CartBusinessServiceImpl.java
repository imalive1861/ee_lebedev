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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Рассчет суммы позиции с учетом скидки пользователя.
     *
     * @param price  - сумма позиции
     * @param sale   - скидка пользователя
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
     *
     * @param login - логин пользователя
     * @return сумма заказа пользователя
     */
    private BigDecimal getAllSumPrice(String login) {
        BigDecimal sum = new BigDecimal(0.00);
        List<Cart> carts = cart.get(login).getCarts();
        if (!carts.isEmpty()) {
            for (Cart c : carts) {
                sum = sum.add(c.getSumPrice());
            }
        }
        return sum;
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
        BigDecimal sumPrice = getSumPriceWithDiscount(
                flower.getPrice(),
                user.getDiscount(),
                number);
        if (cart == null) {
            cart = new Cart.Builder()
                    .order(order)
                    .flower(flower)
                    .number(0)
                    .sumPrice(new BigDecimal(0.00))
                    .build();
            this.cart.get(login).getCarts().add(cart);
        }
        flower = cart.getFlower();
        int i = flower.getNumber() - (cart.getNumber() + number);
        if (i < 0) {
            return false;
        }
        cart.setNumber(cart.getNumber() + number);
        cart.setSumPrice(cart.getSumPrice().add(sumPrice));
        order.setSumPrice(getAllSumPrice(login));
        user.getOrders().add(order);
        return true;
    }

    @Override
    public void setCart(String login) {
        cart.putIfAbsent(login, new Order.Builder()
                .status(OrderStatus.OPENED)
                .carts(new ArrayList<>())
                .build());
        cart.get(login);
    }

    @Override
    public void clear(String login) {
        Order order = new Order.Builder()
                .status(OrderStatus.OPENED)
                .carts(new ArrayList<>())
                .build();
        cart.put(login, order);
    }

    @Override
    public void deleteFlowerFromCart(Long flowerId, String login) {
        Order order = getCartById(login);
        List<Cart> carts = order.getCarts();
        for (Cart c : carts) {
            if (c.getFlower().getId().equals(flowerId)) {
                carts.remove(c);
                break;
            }
        }
        order.setSumPrice(getAllSumPrice(login));
    }
}