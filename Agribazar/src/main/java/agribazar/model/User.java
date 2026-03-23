package agribazar.model;

@lombok.Setter
@lombok.Getter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
@jakarta.persistence.Entity
public class User {
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	private Long id;
	private String email;
	private String password;
	private String name;
	@jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
	private Role role;

}
