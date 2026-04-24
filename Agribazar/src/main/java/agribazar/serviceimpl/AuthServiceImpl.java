package agribazar.serviceimpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import agribazar.dtos.LoginResponse;
import agribazar.dtos.UserDto;
import agribazar.exception.ResourceNotFoundException;
import agribazar.model.Cart;
import agribazar.model.User;
import agribazar.model.WishList;
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

	
	@Override
	public User register(UserDto dto) {

		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new agribazar.exception.ResourceAlreadyExistException("User already exists");
		}
		WishList wishList=new WishList();
       Cart cart=new Cart(); 
       User user = new User();
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setRole(dto.getRole());
		user.setCart(cart);
		user.setWishList(wishList);
		wishList.setUser(user);
		cart.setUser(user);
		return userRepository.save(user);
	}

	@Override
	public LoginResponse verify(UserDto dto) {
	    Authentication authentication = authManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                    dto.getEmail(),
	                    dto.getPassword()
	            )
	    );
	    if (authentication.isAuthenticated()) {

	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	        String token = jwtService.generateToken(userDetails);

	        String role = userDetails.getAuthorities()
	                .iterator()
	                .next()
	                .getAuthority();

	        return new LoginResponse("login successful", role, token);
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
