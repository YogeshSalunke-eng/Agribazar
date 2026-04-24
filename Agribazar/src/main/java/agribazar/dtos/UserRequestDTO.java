package agribazar.dtos;
@lombok.Data
public class UserRequestDTO {
	private String email;
	private String name;
	private String villageName;
	private CartResponseDTO cart;
}
