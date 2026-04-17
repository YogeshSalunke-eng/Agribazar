package agribazar.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishListResponseDTO {
    private Long wishlistId;
    private List<WishListItemResponseDTO> items;
}