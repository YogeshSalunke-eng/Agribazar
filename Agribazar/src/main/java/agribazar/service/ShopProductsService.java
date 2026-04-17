package agribazar.service;

import java.util.List;


import agribazar.dtos.ShopProductsDTO;
import agribazar.model.Category;

public interface ShopProductsService {
public List<ShopProductsDTO> getShopProducts(Long shopId);
List<ShopProductsDTO> getShopProductsCategory(Long shopId, Category category);
}
