package agribazar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import agribazar.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

	java.util.Optional<User> findByEmail(String username);
Optional<User> findByName(String username);
	boolean existsByEmail(String email);

}
