package agribazar.serviceimpl;

import java.net.Authenticator.RequestorType;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import agribazar.dtos.*;
import agribazar.model.*;
import agribazar.repository.*;
import agribazar.service.CartService;
import jakarta.transaction.Transactional;
@Transactional
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private ShopProductsRepository shopProductsRepo;

    @Override
    public void addToCart(CartItemRequestDTO itemrequest, Long userId) {
    	if (itemrequest.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    	 ShopProducts sp = shopProductsRepo.findById(itemrequest.getShopProductId())
    	            .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

    CartItem existing = cartItemRepo
            .findByCartIdAndShopProductsId(cart.getId(),
            		itemrequest.getShopProductId())
            .orElse(null);   
    if(existing!=null) {
    	existing.setQuantity(existing.getQuantity()+1);
    	
      cartItemRepo.save(existing);	
    }
    else {
    	CartItem item = new CartItem();
    	item.setCart(cart);
    	item.setQuantity(itemrequest.getQuantity());
    	item.setShopProducts(sp);
    	cart.getItems().add(item);
    }
       
  
    }
    @Override
  
    public void removeFromCart(Long cartId, Long shopProductId) {
        cartItemRepo.deleteItem(cartId, shopProductId);
    }

    @Override
    public CartResponseDTO getCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId)
          .orElseThrow(() -> new RuntimeException("Cart not found"));
        List<CartItemResponseDTO> items = cart.getItems().stream().map(ci -> {
            CartItemResponseDTO dto = new CartItemResponseDTO();
            dto.setId(ci.getId());
            dto.setQuantity(ci.getQuantity());
            dto.setProductId(ci.getShopProducts().getProduct().getId());
            dto.setProductName(ci.getShopProducts().getProduct().getName());
            dto.setImageUrl(ci.getShopProducts().getProduct().getImageUrl());
            dto.setPrice(ci.getShopProducts().getPrice());
            dto.setShopname(ci.getShopProducts().getShops().getName());
            dto.setShopProductId(ci.getShopProducts().getId());
            dto.setCartId(ci.getCart().getId());
            return dto;
        }).collect(Collectors.toList());
        CartResponseDTO response = new CartResponseDTO();
        response.setItems(items);
        response.setCartId(cart.getId());
        return response;
    }
}