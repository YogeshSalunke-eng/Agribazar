package agribazar.controller;

import java.util.List;
import agribazar.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agribazar.dtos.ShopProductsDTO;
import agribazar.service.ShopProductsService;

@RestController
@RequestMapping("/api")
public class ShopProductsController {
@Autowired
private ShopProductsService shopProductsService;	
	@GetMapping("/shops/{shopId}/products")
	 public List<ShopProductsDTO> getShopProducts(@PathVariable Long shopId){
		 return shopProductsService.getShopProducts(shopId);
	 }
	@GetMapping("shops/{shopId}/category/{category}/products")
	public List<ShopProductsDTO>getShopProductsDTOs(@PathVariable Long shopId,
			@PathVariable String category){
		Category categoryEnum=Category.valueOf(category.toUpperCase());
		return shopProductsService.getShopProductsCategory(shopId, categoryEnum);
	}
}
