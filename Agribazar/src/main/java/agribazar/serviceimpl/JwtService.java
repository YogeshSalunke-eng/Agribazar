package agribazar.serviceimpl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;

@org.springframework.stereotype.Service
public class JwtService {
	@Value("${jwt.secret}")
	private String secretkey;
	@Value("${jwt.expiration-ms}")
	private long expirationMs;

	public String generateToken(UserDetails userDetails) {

		java.util.Map<String, Object> claims = new HashMap<>();

		claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());

		return Jwts.builder().claims(claims).subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expirationMs)).signWith(getkey()).compact();
	}

	private javax.crypto.SecretKey getkey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretkey);
		return io.jsonwebtoken.security.Keys.hmacShaKeyFor(keyBytes);
	}

	public io.jsonwebtoken.Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getkey()).build().parseSignedClaims(token).getPayload();
	}

	public String extractRole(String token) {
		return extractAllClaims(token).get("role", String.class);
	}

	public String extractEmail(String token) {
		return extractAllClaims(token).getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		return extractEmail(token).equals(userDetails.getUsername()) && !isTokenExpired(token);

	}

	public boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

}
