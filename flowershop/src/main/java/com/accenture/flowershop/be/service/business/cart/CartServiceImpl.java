package com.accenture.flowershop.be.service.business.cart;

import com.accenture.flowershop.fe.dto.CustomerCartDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.math.RoundingMode.UP;

@Service
public class CartServiceImpl implements CartService {

    private Map<String, List<CustomerCartDTO>> cart = new TreeMap<>();

    public CartServiceImpl(){
    }

    public CustomerCartDTO getCartById(String login, long flowerId){
        for (CustomerCartDTO i: cart.get(login)) {
            if (i.getFlowerDTO().getId() == flowerId) {
                return i;
            }
        }
        return null;
    }

    public List<CustomerCartDTO> setCartFromSession(String login){
        if (!cart.containsKey(login)){
            cart.put(login, new ArrayList<>());
        }
        return cart.get(login);
    }

    public void addFlowerToCart(String login, FlowerDTO flowerDTO, int number, BigDecimal sumPrice){
        if (cart.containsKey(login)) {
            CustomerCartDTO customerCartDTO = new CustomerCartDTO(flowerDTO, number, sumPrice);
            flowerDTO.setNumber(flowerDTO.getNumber() - number);
            this.cart.get(login).add(customerCartDTO);
        }
    }

    public void edit(String login, long flowerId, int number, BigDecimal sumPrice){
        CustomerCartDTO i = getCartById(login, flowerId);
        if (i != null) {
            i.setNumber(i.getNumber() + number);
            i.getFlowerDTO().setNumber(i.getFlowerDTO().getNumber() - number);
            i.setSumPrice(i.getSumPrice().add(sumPrice));
        }
    }

    public void clear(String login){
        this.cart.get(login).clear();
    }

    public List<CustomerCartDTO> getCartByUserLogin(String login) {
        return cart.get(login);
    }

    public BigDecimal getAllSumPrice(int sale, String login){
        BigDecimal sum = new BigDecimal(0.00);
        if (!cart.isEmpty()) {
            for (CustomerCartDTO c : cart.get(login)) {
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
