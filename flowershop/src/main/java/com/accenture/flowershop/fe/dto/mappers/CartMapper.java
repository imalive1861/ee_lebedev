package com.accenture.flowershop.fe.dto.mappers;

import com.accenture.flowershop.be.entity.Cart;
import com.accenture.flowershop.fe.dto.CartDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, FlowerMapper.class})
public interface CartMapper {
    CartDTO cartToCartDto(Cart cart);
    Cart cartDtoToCart(CartDTO cartDTO);
    List<CartDTO> cartToCartDtos(List<Cart> carts);
    List<Cart> cartDtosToCart(List<CartDTO> carts);
}
