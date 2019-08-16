package com.accenture.flowershop.fe.servicedto.cartdto;

import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

public interface CartService {
    boolean isAddFlowerToCart(UserDTO userDTO, FlowerDTO flowerDTO, int number);
    OrderDTO clear(String login);
    OrderDTO setCartFromSession(String login);
}
