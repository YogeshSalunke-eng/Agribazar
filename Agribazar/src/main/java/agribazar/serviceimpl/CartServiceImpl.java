package agribazar.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agribazar.dtos.*;
import agribazar.model.*;
import agribazar.repository.*;
import agribazar.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private ShopProductsRepository shopProductsRepo;

    @Override
    public void addToCart(CartItemRequestDTO request) {

        Cart cart = cartRepo.findById(request.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        ShopProducts sp = shopProductsRepo.findById(request.getShopProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // check if already exists
        CartItem existing = cartItemRepo
                .findByCartIdAndShopProductsId(request.getCartId(), request.getShopProductId())
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            cartItemRepo.save(existing);
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setShopProducts(sp);
            item.setQuantity(request.getQuantity());
            cartItemRepo.save(item);
        }
    }

    @Override
    public void removeFromCart(Long cartId, Long shopProductId) {
        cartItemRepo.deleteByCartIdAndShopProductsId(cartId, shopProductId);
    }

    @Override
    public CartResponseDTO getCart(Long cartId) {

        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItemResponseDTO> items = cart.getItems().stream().map(ci -> {
            CartItemResponseDTO dto = new CartItemResponseDTO();

            dto.setId(ci.getId());
            dto.setQuantity(ci.getQuantity());

            dto.setProductId(ci.getShopProducts().getProduct().getId());
            dto.setProductName(ci.getShopProducts().getProduct().getName());
            dto.setImageUrl(ci.getShopProducts().getProduct().getImageUrl());
            dto.setPrice(ci.getShopProducts().getPrice());

            return dto;
        }).collect(Collectors.toList());

        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(cartId);
        response.setItems(items);

        return response;
    }
}