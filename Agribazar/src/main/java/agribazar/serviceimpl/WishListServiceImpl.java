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
    public void addToWishlist(WishListRequestDTO request, Long userId) {
        WishList wishlist = wishListRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        ShopProducts sp = shopProductsRepo.findById(request.getShopProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        boolean exists = wishListItemRepo
                .findByWishlistIdAndShopProductId(request.getWishlistId(), request.getShopProductId())
                .isPresent();

        if (!exists) {
            WishListItem item = new WishListItem();
            item.setWishlist(wishlist);
            item.setShopProduct(sp);
            wishlist.getItems().add(item);
            wishListItemRepo.save(item);
        }
    }

    @Override
    public void removeFromWishlist(Long wishlistId, Long shopProductId) {
        wishListItemRepo.deleteItem(wishlistId, shopProductId);
    }

    @Override
    public WishListResponseDTO getWishlist(Long userId) {

        WishList wishlist = wishListRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        List<WishListItemResponseDTO> items = wishlist.getItems().stream().map(w -> {
            WishListItemResponseDTO dto = new WishListItemResponseDTO();
            dto.setId(w.getId());
            dto.setProductId(w.getShopProduct().getProduct().getId());
            dto.setProductName(w.getShopProduct().getProduct().getName());
            dto.setImageUrl(w.getShopProduct().getProduct().getImageUrl());
            dto.setPrice(w.getShopProduct().getPrice());
            dto.setShopName(w.getShopProduct().getShops().getName());
            dto.setDescription(w.getShopProduct().getProduct().getDescription());
            dto.setShopProductId(w.getShopProduct().getId());
            dto.setWishListId(w.getWishlist().getId());
            dto.setCropname(w.getShopProduct().getProduct().getCropname());
            return dto;
        }).collect(Collectors.toList());

        WishListResponseDTO response = new WishListResponseDTO();
        response.setWishlistId(wishlist.getId());
        response.setItems(items);
        return response;
    }
}