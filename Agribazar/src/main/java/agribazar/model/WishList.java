package agribazar.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WishList {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
private Long id;
@OneToMany(mappedBy = "wishlist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
private List<WishListItem> items;
@OneToOne
private User user;
}
