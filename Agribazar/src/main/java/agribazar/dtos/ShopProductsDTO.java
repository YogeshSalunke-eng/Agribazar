package agribazar.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ShopProductsDTO {
private int productId;
private Long id;
private int shopId;
private String price;
private int quantity;
private ProductDTO product;
private ShopsDTO shops;

}
