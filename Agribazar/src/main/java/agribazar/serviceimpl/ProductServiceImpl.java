package agribazar.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;

import agribazar.dtos.ProductDTO;
import agribazar.model.Category;
import agribazar.model.Product;
import agribazar.repository.ProductRepository;

@org.springframework.stereotype.Service
@lombok.RequiredArgsConstructor
public class ProductServiceImpl implements agribazar.service.ProductService {
	private final ModelMapper modelMapper;
	private final ProductRepository productRepository;

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream()
				.map(product -> modelMapper.map(product, agribazar.dtos.ProductDTO.class))
				.collect(java.util.stream.Collectors.toList());

	}

	@Override
	public List<ProductDTO> getProductsByCategory(Category category) {
		return productRepository.findByCategory(category).stream()
				.map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(java.util.stream.Collectors.toList());

	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = modelMapper.map(productDTO, Product.class);
		Product savedproduct = productRepository.save(product);
		return modelMapper.map(savedproduct, ProductDTO.class);
	}

}
