package agribazar.service;

import agribazar.dtos.CartItemRequestDTO;
import agribazar.dtos.CartResponseDTO;

public interface CartService {
    void addToCart(CartItemRequestDTO request);
    void removeFromCart(Long cartId, Long shopProductId);
    CartResponseDTO getCart(Long cartId);
}