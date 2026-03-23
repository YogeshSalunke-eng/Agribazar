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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long price;
	private String imageUrl;
	private String description;
	@jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
	private Category category;
	@jakarta.persistence.ManyToOne
	@jakarta.persistence.JoinColumn(name = "shop_id")
	private Shops shop;
}
