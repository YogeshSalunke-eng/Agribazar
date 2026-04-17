package agribazar.service;

import java.util.List;

import agribazar.dtos.ProductDTO;
import agribazar.model.Category;

public interface ProductService {

	public List<ProductDTO> getAllProducts();

	public List<ProductDTO> getProductsByCategory(Category category);

	public ProductDTO createProduct(ProductDTO productDTO);

	List<ProductDTO> getProductsByCategory(Category category, int page, int size);
}
