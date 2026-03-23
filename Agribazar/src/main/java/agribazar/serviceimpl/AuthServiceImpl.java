package agribazar.serviceimpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import agribazar.dtos.UserDto;
import agribazar.exception.ResourceNotFoundException;
import agribazar.model.User;
import agribazar.repository.EmailOtpRepository;
import agribazar.repository.UserRepository;

@org.springframework.stereotype.Service
public class AuthServiceImpl implements agribazar.service.AuthService {
	@org.springframework.beans.factory.annotation.Autowired
	private JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	@org.springframework.beans.factory.annotation.Autowired
	private UserRepository userRepository;
	@org.springframework.beans.factory.annotation.Autowired
	private AuthenticationManager authManager;

	AuthServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@org.springframework.beans.factory.annotation.Autowired
	private EmailOtpRepository emailOtpRepository;

	@Override
	public User register(UserDto dto) {
//		EmailOtp emailOtp = emailOtpRepository.findTopByEmailOrderByExpiryTimeDesc(dto.getEmail())
//				.orElseThrow(() -> new RuntimeException("otp not found"));
//
//		if (!emailOtp.isVerified()) {
//			throw new RuntimeException("Email not verified");
//		}

		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new agribazar.exception.ResourceAlreadyExistException("User already exists");
		}

		User user = new User();
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setRole(agribazar.model.Role.ROLE_USER);
		return userRepository.save(user);
	}

	@Override
	public String verify(UserDto dto) {

		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
		if (authentication.isAuthenticated()) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return jwtService.generateToken(userDetails);
		}
		throw new BadCredentialsException("Invalid email or password");
	}

	@Override
	public User forgetUser(UserDto dto) {
		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Email not registered"));
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		return userRepository.save(user);
	}

}
