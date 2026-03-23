package agribazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import agribazar.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	java.util.Optional<User> findByEmail(String username);

	boolean existsByEmail(String email);

}
