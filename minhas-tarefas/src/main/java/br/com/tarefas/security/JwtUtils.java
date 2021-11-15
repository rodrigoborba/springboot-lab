package br.com.tarefas.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	@Value("${app.jwt.SecretKey}")
	private String jwtSecret;
	
	@Value("${app.jwt.ExpirationMs}")
	private Integer jwtExtipartionMs;
	
	public String generateJwtToken(Authentication authetication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authetication.getPrincipal();
		
		
		Date currentTime = new Date();
		Date expirationTime = new Date(currentTime.getTime() + jwtExtipartionMs);
		
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(currentTime)
				.setExpiration(expirationTime)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
