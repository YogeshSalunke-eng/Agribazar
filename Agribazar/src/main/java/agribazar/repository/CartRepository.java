package agribazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import agribazar.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}