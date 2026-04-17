package agribazar.service;

import agribazar.dtos.WishListRequestDTO;
import agribazar.dtos.WishListResponseDTO;

public interface WishListService {
    void addToWishlist(WishListRequestDTO request);
    void removeFromWishlist(Long wishlistId, Long shopProductId);
    WishListResponseDTO getWishlist(Long wishlistId);
}