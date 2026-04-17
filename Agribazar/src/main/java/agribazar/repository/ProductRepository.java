package agribazar.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import agribazar.model.Category;
import agribazar.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByCategory(Category category, Pageable pageable);
}
