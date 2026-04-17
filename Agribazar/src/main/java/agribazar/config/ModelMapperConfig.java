package agribazar.config;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import agribazar.dtos.ShopProductsDTO;
import agribazar.model.ShopProducts;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
	    ModelMapper modelMapper = new ModelMapper();
	    modelMapper.typeMap(ShopProducts.class, ShopProductsDTO.class)
	        .addMappings(mapper -> {
	            mapper.map(src -> src.getShops().getId(), ShopProductsDTO::setShopId);
	            mapper.map(src -> src.getProduct().getId(), ShopProductsDTO::setProductId);
	     
	        
	        });
	    return modelMapper;

	}
}