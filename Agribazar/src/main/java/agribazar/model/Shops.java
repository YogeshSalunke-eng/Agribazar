package agribazar.model;

import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@jakarta.persistence.Entity
public class Shops {
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@jakarta.persistence.Id
	private Long id;
	private String name;
	private String ownername;
	
}
