package agribazar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import agribazar.model.Cart;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<Cart> findByUserId(Long userId);
}