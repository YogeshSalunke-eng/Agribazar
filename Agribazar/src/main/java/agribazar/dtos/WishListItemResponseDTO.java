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
    private String shopName;
    private String cropname;
    private String description;
    private Long wishListId;
    private Long shopProductId;
}


