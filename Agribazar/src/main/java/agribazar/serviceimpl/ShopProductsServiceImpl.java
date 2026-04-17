package agribazar.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agribazar.dtos.ProductDTO;
import agribazar.dtos.ShopProductsDTO;
import agribazar.model.Category;
import agribazar.model.ShopProducts;
import agribazar.repository.ShopProductsRepository;
import agribazar.service.ShopProductsService;
@Service
public class ShopProductsServiceImpl implements ShopProductsService {
	@Autowired
 private ShopProductsRepository repo;
	 @Autowired
	private ModelMapper modelMapper;
	@Override
	public List<ShopProductsDTO> getShopProducts(Long shopId) {
		List<ShopProducts> list=repo.findByShopsId(shopId);
		
		return list.stream()
				.map(sp->modelMapper.map(sp, ShopProductsDTO.class))
				.toList();
	}
	@Override
	public List<ShopProductsDTO> getShopProductsCategory(Long shopId, Category category) {
List<ShopProducts> list =repo.findByShopsIdAndProductCategory(shopId, category);

		return list.stream()
				.map(sp-> modelMapper.map(sp,ShopProductsDTO.class))
						.toList();
		
	}

}
