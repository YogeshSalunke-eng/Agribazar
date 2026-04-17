package agribazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Entity
public class Product {

	@Id
	private Long id;
	private String name;
	private String price;
	private String imageUrl;
	private String description;
	private String cropname;
	@jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
	private Category category;
	
}
