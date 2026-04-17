package agribazar.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import agribazar.dtos.ShopsDTO;
import agribazar.model.Shops;

public interface ShopsRepository extends JpaRepository<Shops, Long>{
public List<ShopsDTO> findById(int id);
}
