package agribazar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import agribazar.dtos.WishListRequestDTO;
import agribazar.dtos.WishListResponseDTO;
import agribazar.service.WishListService;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    // ✅ Add item
    @PostMapping("/add")
    public String addToWishlist(@RequestBody WishListRequestDTO request) {
        wishListService.addToWishlist(request);
        return "Added to wishlist";
    }

    // ✅ Remove item
    @DeleteMapping("/remove")
    public String removeFromWishlist(@RequestParam Long wishlistId,
                                     @RequestParam Long shopProductId) {
        wishListService.removeFromWishlist(wishlistId, shopProductId);
        return "Removed from wishlist";
    }

    // ✅ Get wishlist
    @GetMapping("/{wishlistId}")
    public WishListResponseDTO getWishlist(@PathVariable Long wishlistId) {
        return wishListService.getWishlist(wishlistId);
    }
}