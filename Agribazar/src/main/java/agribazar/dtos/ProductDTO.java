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

	private String name;
	private String price;
	private String imageUrl;
	private String description;
	private Category category;
	private String cropname;
}