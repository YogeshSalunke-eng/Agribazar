package agribazar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import agribazar.dtos.WishListRequestDTO;
import agribazar.dtos.WishListResponseDTO;
import agribazar.model.UserPrincipal;
import agribazar.service.WishListService;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @PostMapping("/add")
    public String addToWishlist(@RequestBody WishListRequestDTO request,
    		@AuthenticationPrincipal UserPrincipal userdetails) {
Long userId= userdetails.getId();
        wishListService.addToWishlist(request,userId);
        return "Added to wishlist";
    }

    @DeleteMapping("/remove")
    public String removeFromWishlist(@RequestParam Long wishlistId,
                                     @RequestParam Long shopProductId) {
        wishListService.removeFromWishlist(wishlistId, shopProductId);
        return "Removed from wishlist";
    }

    @GetMapping("/get")
    public WishListResponseDTO getWishlist(@AuthenticationPrincipal UserPrincipal userdetails) {
    	Long userId=userdetails.getId();
        return wishListService.getWishlist(userId);
    }
}