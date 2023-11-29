package com.example.nutribalance.security.jwt;

import java.util.Calendar;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.nutribalance.dto.LocalUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;

@Service
public class TokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

	private static String TOKEN_SECRET;
	private static Long ACCESS_TOKEN_VALIDITY;

	public TokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long accessValidity) {
		Assert.notNull(accessValidity, "Validity must not be null");
		Assert.hasText(secret, "Validity must not be null or empty");
		TOKEN_SECRET = secret;
		ACCESS_TOKEN_VALIDITY = accessValidity;

	}


	public String createToken(Authentication authentication) {
		LocalUser userPrincipal = (LocalUser) authentication.getPrincipal();
		return Jwts.builder()
				.setId(Long.toString(userPrincipal.getUser().getUser_id()))
				.setSubject(userPrincipal.getUser().getUsername())
				.setIssuedAt(new Date())
				.setIssuer("app-Service")
				.setExpiration(calcTokenExpirationDate())
				.claim("created", Calendar.getInstance().getTime())
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();

	}

	public Long getUserIdFromToken(String token) {
		Claims claims = getClaims(token);
		return Long.parseLong(claims.getId());
	}
	private static Date calcTokenExpirationDate() {
		return new Date(System.currentTimeMillis() + (ACCESS_TOKEN_VALIDITY) * 1000);
	}

	public String getUserNameFromToken(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}
	public boolean isTokenValid(String token, LocalUser userDetails) {
		String username = getUserNameFromToken(token);
		boolean isUserNameEqual = username.equalsIgnoreCase(userDetails.getUsername()) ;
		boolean isUserNameEqualMail = username.equalsIgnoreCase(userDetails.getEmail()) ;
		return ((isUserNameEqual||isUserNameEqualMail) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		Date expiration = getClaims(token).getExpiration();
		return expiration.before(new Date());
	}

	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
	}


	public boolean validateToken(String token , HttpServletRequest httpServletRequest){

		try {
			Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token);
			return true;
		}catch (SignatureException ex){
			httpServletRequest.setAttribute("Invalid JWT Signature",ex.getMessage());
			//  throw new SecurityException("Invalid JWT Signature");
		}catch (MalformedJwtException ex){
			httpServletRequest.setAttribute("Invalid JWT token",ex.getMessage());
			//  throw new SecurityException("Invalid JWT token");
		}catch (ExpiredJwtException ex){
			httpServletRequest.setAttribute("Expired JWT token",ex.getMessage());
			//  throw new SecurityException("security.token_expired");
		}catch (UnsupportedJwtException ex){
			httpServletRequest.setAttribute("Unsupported JWT exception",ex.getMessage());
			//   throw new SecurityException("Unsupported JWT exception");
		}catch (IllegalArgumentException ex){
			httpServletRequest.setAttribute("Jwt claims string is empty",ex.getMessage());
			//   throw new SecurityException("Jwt claims string is empty");
		}
		return false;
	}
}