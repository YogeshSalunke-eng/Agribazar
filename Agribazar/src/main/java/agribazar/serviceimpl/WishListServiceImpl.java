package agribazar.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agribazar.dtos.*;
import agribazar.model.*;
import agribazar.repository.*;
import agribazar.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishListRepository wishListRepo;

    @Autowired
    private WishListItemRepository wishListItemRepo;

    @Autowired
    private ShopProductsRepository shopProductsRepo;

    @Override
    public void addToWishlist(WishListRequestDTO request) {

        WishList wishlist = wishListRepo.findById(request.getWishlistId())
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        ShopProducts sp = shopProductsRepo.findById(request.getShopProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // avoid duplicates
        boolean exists = wishListItemRepo
                .findByWishlistIdAndShopProductId(request.getWishlistId(), request.getShopProductId())
                .isPresent();

        if (!exists) {
            WishListItem item = new WishListItem();
            item.setWishlist(wishlist);
            item.setShopProduct(sp);
            wishListItemRepo.save(item);
        }
    }

    @Override
    public void removeFromWishlist(Long wishlistId, Long shopProductId) {
        wishListItemRepo.deleteByWishlistIdAndShopProductId(wishlistId, shopProductId);
    }

    @Override
    public WishListResponseDTO getWishlist(Long wishlistId) {

        WishList wishlist = wishListRepo.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        List<WishListItemResponseDTO> items = wishlist.getItems().stream().map(w -> {
            WishListItemResponseDTO dto = new WishListItemResponseDTO();

            dto.setId(w.getId());

            dto.setProductId(w.getShopProduct().getProduct().getId());
            dto.setProductName(w.getShopProduct().getProduct().getName());
            dto.setImageUrl(w.getShopProduct().getProduct().getImageUrl());
            dto.setPrice(w.getShopProduct().getPrice());

            return dto;
        }).collect(Collectors.toList());

        WishListResponseDTO response = new WishListResponseDTO();
        response.setWishlistId(wishlistId);
        response.setItems(items);

        return response;
    }
}