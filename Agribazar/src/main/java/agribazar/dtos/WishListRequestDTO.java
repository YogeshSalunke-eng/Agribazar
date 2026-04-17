package agribazar.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishListRequestDTO {
    private Long wishlistId;
    private Long shopProductId;
}