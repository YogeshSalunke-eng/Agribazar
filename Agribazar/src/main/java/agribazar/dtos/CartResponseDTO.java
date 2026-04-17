package agribazar.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDTO {
    private Long cartId;
    private List<CartItemResponseDTO> items;
}