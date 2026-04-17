package agribazar.config;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import agribazar.serviceimpl.CustomUserServiceImpl;
import agribazar.serviceimpl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Component
public class JwtTokenValidator extends OncePerRequestFilter {
	@org.springframework.beans.factory.annotation.Autowired
	private JwtService jwtService;
	@org.springframework.beans.factory.annotation.Autowired
	private ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = null;
		String username = null;

		jakarta.servlet.http.Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (jakarta.servlet.http.Cookie cookie : cookies) {
				if ("jwt".equals(cookie.getName())) {
					token = cookie.getValue();
				}
			}
		}
		if (token != null) {
			username = jwtService.extractEmail(token);
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = context.getBean(CustomUserServiceImpl.class).loadUserByUsername(username);
			if (jwtService.validateToken(token, userDetails)) {
				String role = jwtService.extractRole(token);

				org.springframework.security.core.authority.SimpleGrantedAuthority authority = 
						new org.springframework.security.core.authority.SimpleGrantedAuthority(
						role);

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, java.util.List.of(authority));
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}

		}
		filterChain.doFilter(request, response);
		System.out.println("TOKEN: " + token);
		System.out.println("EXTRACTED USERNAME: " + username);
	}
}
