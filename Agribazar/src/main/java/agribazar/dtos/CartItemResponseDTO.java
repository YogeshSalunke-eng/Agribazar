package agribazar.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDTO {
    private Long id;
    private int quantity;

    private Long productId;
    private String productName;
    private String imageUrl;
    private String price;
}