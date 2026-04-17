package agribazar.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishListItemResponseDTO {
    private Long id;

    private Long productId;
    private String productName;
    private String imageUrl;
    private String price;
}