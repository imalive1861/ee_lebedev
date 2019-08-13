package com.accenture.flowershop.fe.servicedto.cartdto;

import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.mappers.FlowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.math.RoundingMode.UP;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private FlowerMapper flowerMapper;

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    private Map<String, List<CartDTO>> cart = new TreeMap<>();

    public CartServiceImpl(){
    }

    public CartDTO getCartById(String login, long flowerId){
        for (CartDTO i: cart.get(login)) {
            if (i.getFlower().getId() == flowerId) {
                return i;
            }
        }
        return null;
    }

    public List<CartDTO> setCartFromSession(String login){
        if (!cart.containsKey(login)){
            cart.put(login, new ArrayList<>());
        }
        return cart.get(login);
    }

    public boolean isAddFlowerToCart(String login, long flowerId, int number){
        if (number < 0) {
            return false;
        }
        FlowerDTO flowerDTO;
        CartDTO cart = getCartById(login, flowerId);
        if (cart == null) {
            flowerDTO = flowerMapper.flowerToFlowerDto(flowerBusinessService.get(flowerId));
            cart = new CartDTO(null, flowerDTO,0,new BigDecimal(0.00));
            this.cart.get(login).add(cart);
        }
        flowerDTO = cart.getFlower();
        int i = flowerDTO.getNumber() - number;
        if (i < 0) {
            return false;
        }
        BigDecimal sumPrice = flowerDTO.getPrice().multiply(new BigDecimal(number));
        cart.setNumber(cart.getNumber() + number);
        flowerDTO.setNumber(flowerDTO.getNumber() - number);
        cart.setSumPrice(cart.getSumPrice().add(sumPrice));
        return true;
    }

    public void clear(String login){
        this.cart.get(login).clear();
    }

    public List<CartDTO> getCartByUserLogin(String login) {
        return cart.get(login);
    }

    public BigDecimal getAllSumPrice(int sale, String login){
        BigDecimal sum = new BigDecimal(0.00);
        if (!cart.isEmpty()) {
            for (CartDTO c : cart.get(login)) {
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
