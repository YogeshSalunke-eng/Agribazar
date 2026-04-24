package agribazar.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDTO {
    private Long id;
    private int quantity;
    private String shopname;
    private Long productId;
    private String productName;
    private String imageUrl;
    private String price;
    private Long shopProductId;
    private Long cartId;
}