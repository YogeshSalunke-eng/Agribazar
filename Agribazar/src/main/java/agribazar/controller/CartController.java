package agribazar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import agribazar.dtos.CartItemRequestDTO;
import agribazar.dtos.CartResponseDTO;
import agribazar.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody CartItemRequestDTO request) {
        cartService.addToCart(request);
        return "Product added to cart";
    }

    @DeleteMapping("/remove")
    public String removeFromCart(@RequestParam Long cartId,
                                @RequestParam Long shopProductId) {
        cartService.removeFromCart(cartId, shopProductId);
        return "Product removed from cart";
    }

    @GetMapping("/{cartId}")
    public CartResponseDTO getCart(@PathVariable Long cartId) {
        return cartService.getCart(cartId);
    }
}