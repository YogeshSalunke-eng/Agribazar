package agribazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import agribazar.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {
}