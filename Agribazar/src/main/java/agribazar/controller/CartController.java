package agribazar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import agribazar.dtos.CartItemRequestDTO;
import agribazar.dtos.CartResponseDTO;
import agribazar.model.UserPrincipal;
import agribazar.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody CartItemRequestDTO request,
    		@AuthenticationPrincipal UserPrincipal userdetails) {
    	Long userId= userdetails.getId();
        cartService.addToCart(request,userId);
        return "Product added to cart";
    }

    @DeleteMapping("/remove")
    public String removeFromCart(@RequestParam Long cartId,
                                @RequestParam Long shopProductId) {
        cartService.removeFromCart(cartId, shopProductId);
        return "Product removed from cart";
    }

    @GetMapping("/getcart")
    public CartResponseDTO getCart(@AuthenticationPrincipal UserPrincipal userdetails) {
    	Long userId=userdetails.getId();
        return cartService.getCart(userId);
    }
}