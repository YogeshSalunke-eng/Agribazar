package agribazar.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import agribazar.dtos.LoginResponse;
import agribazar.dtos.UserDto;
import agribazar.dtos.UserRequestDTO;
import agribazar.model.User;
import agribazar.repository.UserRepository;
import agribazar.service.AuthService;
import agribazar.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	@Autowired
private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto dto) {
		return ResponseEntity.ok(authService.register(dto));
	}
	
	@PutMapping("/complete-profile")
	public ResponseEntity<?> completeProfile(
	        @RequestBody UserDto dto) {
         String email=dto.getEmail();
         User user = userRepository.findByEmail(email)
                 .orElseThrow(() -> new RuntimeException("User not found"));
         user.setName(dto.getName());
	    user.setRole(dto.getRole());
	    user.setVillageName(dto.getVillageName());
	    userRepository.save(user);
	    return ResponseEntity.ok("Profile updated");
	}
	
	

	@PutMapping("/forgot-password")
	public ResponseEntity<?> forgot(@RequestBody UserDto dto) {
		authService.forgetUser(dto);

		return ResponseEntity.ok("password updated successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto dto, HttpServletResponse response) {

	    LoginResponse res = authService.verify(dto);

	    Cookie cookie = new Cookie("jwt", res.getToken());
	    cookie.setHttpOnly(true);
	    cookie.setPath("/");
	    cookie.setSecure(true);
	    cookie.setMaxAge(24 * 60 * 60);
	    response.addCookie(cookie);

	    return ResponseEntity.ok(res); 
	}
@GetMapping("/me")
	public ResponseEntity<UserRequestDTO> getcurrentUser(){
		return ResponseEntity.ok(userService.getUser());
	}
	

}
