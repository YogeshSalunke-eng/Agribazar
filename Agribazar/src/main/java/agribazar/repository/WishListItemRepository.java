package agribazar.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import agribazar.model.WishListItem;

public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {

    Optional<WishListItem> findByWishlistIdAndShopProductId(Long wishlistId, Long shopProductId);

    void deleteByWishlistIdAndShopProductId(Long wishlistId, Long shopProductId);
}