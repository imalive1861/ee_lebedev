package com.accenture.flowershop.fe.dto.mappers;

import com.accenture.flowershop.be.entity.Cart;
import com.accenture.flowershop.fe.dto.CartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, FlowerMapper.class})
public interface CartMapper {

    @Mapping(target = "order.carts", ignore = true)
    CartDTO cartToCartDto(Cart cart);
    Cart cartDtoToCart(CartDTO cartDTO);
    List<CartDTO> cartToCartDtos(List<Cart> carts);
    List<Cart> cartDtosToCart(List<CartDTO> carts);

    @Named("cartDTOList")
    default List<CartDTO> toEmailDTOList(List<Cart> source) {
        return source
                .stream()
                .peek(email -> email.setOrder(null))
                .map(this::cartToCartDto)
                .collect(Collectors.toList());
    }
}
