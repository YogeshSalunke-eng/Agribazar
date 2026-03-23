package agribazar.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import agribazar.dtos.ProductDTO;
import agribazar.model.Product;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(Product.class, ProductDTO.class)
				.addMappings(mapper -> mapper.map(src -> src.getShop().getName(), ProductDTO::setShopName));

		return modelMapper;

	}
}