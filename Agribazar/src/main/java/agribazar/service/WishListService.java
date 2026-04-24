package agribazar.service;

import agribazar.dtos.WishListRequestDTO;
import agribazar.dtos.WishListResponseDTO;

public interface WishListService {
    void removeFromWishlist(Long wishlistId, Long shopProductId);
    WishListResponseDTO getWishlist(Long wishlistId);
	void addToWishlist(WishListRequestDTO request, Long userId);
}