package agribazar.service;

import agribazar.dtos.CartItemRequestDTO;
import agribazar.dtos.CartResponseDTO;

public interface CartService {
    void removeFromCart(Long cartId, Long shopProductId);
    CartResponseDTO getCart(Long cartId);
	void addToCart(CartItemRequestDTO cartItemrequest, Long userId);
}