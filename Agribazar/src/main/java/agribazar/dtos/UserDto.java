package agribazar.dtos;
import agribazar.model.Role;
@lombok.Data
public class UserDto {
	private String email;
	private String password;
	private String name;
	private Role role;
	private String villageName;
}
