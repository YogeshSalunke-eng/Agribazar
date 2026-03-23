package agribazar.dtos;

import agribazar.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private Long price;
	private String imageUrl;
	private String description;
	private Category category;
	private String shopName;
}