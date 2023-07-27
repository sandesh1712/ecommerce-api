package helper;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {

	@Value("${jwt.secret}")
	private String secretKey;

	public String generateToken(User user) {
		Claims claims = Jwts.claims();
		claims.put("username", user.getUsername());
		return createToken(claims);
	}

	private String createToken(Claims claims) {
		return Jwts.builder().setClaims(claims) // add claims
				.setIssuedAt(new Date(System.currentTimeMillis())) // set issued date
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 6)) // set expiration time to 6hrs
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); // add excryption algorithm
	}

	private Key getSignKey() {
		byte[] bytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(bytes);
	}

	public String extractUserName(String token) {
		Claims claims = extractClaims(token);
		String username = claims.get("username").toString();
		return username;
	}

	private Claims extractClaims(String token) {
		return (Claims) Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	public boolean isValidToken(String token, UserDetails user) {
		String username = this.extractUserName(token); // extract username
		return username.equals(user.getUsername()) && isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return getExpirationDateTime(token).before(new Date());
	}
	
	private Date getExpirationDateTime(String token) {
		return extractClaims(token).getExpiration();
	}
}
