package agribazar.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agribazar.model.WishListItem;
import jakarta.transaction.Transactional;

public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {

    Optional<WishListItem> findByWishlistIdAndShopProductId(Long wishlistId, Long shopProductId);

    @Modifying
    @Transactional
    @Query("DELETE FROM WishListItem w WHERE w.wishlist.id = :wishlistId AND w.shopProduct.id = :shopProductId")
    void deleteItem(@Param("wishlistId") Long wishlistId,
                    @Param("shopProductId") Long shopProductId);
    
}
