package agribazar.serviceimpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import agribazar.model.User;

@org.springframework.stereotype.Service
public class CustomUserServiceImpl implements UserDetailsService {
	@org.springframework.beans.factory.annotation.Autowired
	private agribazar.repository.UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

		return new agribazar.model.UserPrincipal(user);
	}

}
