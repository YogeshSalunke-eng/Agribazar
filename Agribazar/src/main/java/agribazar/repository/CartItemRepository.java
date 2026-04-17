package agribazar.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import agribazar.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndShopProductsId(Long cartId, Long shopProductId);

    void deleteByCartIdAndShopProductsId(Long cartId, Long shopProductId);
}