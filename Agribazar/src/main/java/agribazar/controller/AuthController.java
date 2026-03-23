package agribazar.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import agribazar.dtos.UserDto;
import agribazar.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto dto) {
		return ResponseEntity.ok(authService.register(dto));
	}

	@PutMapping("/forgot-password")
	public ResponseEntity<?> forgot(@RequestBody UserDto dto) {
		authService.forgetUser(dto);

		return ResponseEntity.ok("password updated successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto dto, HttpServletResponse response) {
		String token = authService.verify(dto);
		Cookie cookie = new Cookie("jwt", token);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setSecure(true);
		cookie.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie);
		return ResponseEntity.ok("login successful");
	}

	@org.springframework.web.bind.annotation.GetMapping("/me")
	public ResponseEntity<?> getCurrentUser(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		java.util.List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.toList();
		Map<String, Object> response = new HashMap<>();
		response.put("roles", roles);
		return ResponseEntity.ok(response);
	}

}
