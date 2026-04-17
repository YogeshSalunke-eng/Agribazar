package agribazar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import agribazar.model.Category;
import agribazar.model.ShopProducts;

public interface ShopProductsRepository extends JpaRepository<ShopProducts,Long> {
public List<ShopProducts>findByShopsId(Long shopId);
public List<ShopProducts>findByShopsIdAndProductCategory(Long shopId,Category category);
}
