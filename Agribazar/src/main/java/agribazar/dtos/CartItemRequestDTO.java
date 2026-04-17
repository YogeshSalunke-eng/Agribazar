package agribazar.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDTO {
    private Long cartId;
    private Long shopProductId;
    private int quantity;
}
