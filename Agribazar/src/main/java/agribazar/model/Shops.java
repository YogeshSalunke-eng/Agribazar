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
	private int id;
	private String name;
	private String ownername;
	@jakarta.persistence.OneToMany(mappedBy = "shop")
	private List<Product> products;
}
