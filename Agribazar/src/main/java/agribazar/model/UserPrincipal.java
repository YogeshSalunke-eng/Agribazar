package agribazar.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserPrincipal implements org.springframework.security.core.userdetails.UserDetails {

	private User user;

	public UserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return java.util.List
				.of(new org.springframework.security.core.authority.SimpleGrantedAuthority(user.getRole().name()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

}
