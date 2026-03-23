package agribazar.repository;

import java.util.Optional;

import agribazar.model.EmailOtp;

public interface EmailOtpRepository extends org.springframework.data.jpa.repository.JpaRepository<EmailOtp, Long> {

	Optional<EmailOtp> findTopByEmailOrderByExpiryTimeDesc(String email);
}