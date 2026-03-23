package agribazar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agribazar.dtos.ProductDTO;
import agribazar.model.Category;
import agribazar.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
@lombok.RequiredArgsConstructor
public class ProductController {
	private final agribazar.service.ProductService productService;
	private final ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable String category) {
		Category cat = Category.valueOf(category.toUpperCase());
		return ResponseEntity.ok(productService.getProductsByCategory(cat));

	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.createProduct(productDTO));
	}

}
