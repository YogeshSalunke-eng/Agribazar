package agribazar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import agribazar.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

	Optional<WishList> findByUserId(Long userId);
}