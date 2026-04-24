package agribazar.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agribazar.model.CartItem;
import jakarta.transaction.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndShopProductsId(Long cartId, Long shopProductId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.cart.id = :cartId AND c.shopProducts.id = :shopProductId")
    void deleteItem(@Param("cartId") Long cartId,
                    @Param("shopProductId") Long shopProductId);
}