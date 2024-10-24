package com.hosting.restapi.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hosting.restapi.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${token.secret.key}")
	String jwtSecretKey;
	
	@Value("${token.expirationms}")
	Long jwtExpirationMs;

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
	    // Include user ID in the claims
	    claims.put("userId", ((User) userDetails).getId()); // Modify this line according to your UserDetails implementation
	    // Generate the token
//		return generateToken(new HashMap<>(), userDetails);
	    return generateToken(claims, userDetails);
	}
  
  private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
      return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
      final String userName = extractUserName(token);
      return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }
  
  private boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
  }
  
  private Claims extractAllClaims(String token) {
      return Jwts
        .parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
  }
  
  public String extractUserName(String token) {
      return extractClaim(token, Claims::getSubject);
}

  private Key getSigningKey() {
      byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
      return Keys.hmacShaKeyFor(keyBytes);
  }

}
