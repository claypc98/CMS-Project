package com.example.contractmanagement.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class JwtUtil {
	private String secretkey = "${jwt.secret}";

	/**
	 * Extracts user name by using the given token. If the token is a Bearer token
	 * it returns User name. Else returns null.
	 *
	 * @param token
	 * @return User Name in String format
	 */
	public String extractUsername(String token) {
		try {

			if (!token.substring(0, 7).equals("Bearer ")) {
				return null;
			}

			String token1 = token.substring(7);
			return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token1).getBody().getSubject();
		} catch (Exception e) {
			return null;
		}
	}

	public String extractId(String token) {
		try {
			return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody().getId();
		} catch(Exception e) {
			return null;
		}
	}

	/*
	 * public String extractPayload(String token) { try {
	 *
	 * if (!token.substring(0, 7).equals("Bearer ")) { return null; }
	 *
	 * String token1 = token.substring(7); return
	 * Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token1).getBody().getId
	 * (); } catch (Exception e) { return null; } }
	 */


	/**
	 * Gets an authorized User Details and generated a JWT token with Expiration
	 * time for the particular token
	 *
	 * @param userDetails - User ID,Password
	 * @return token in string format
	 */
	public String generateToken(UserDetails userDetails, String role) {
//		String role = userDetails.getAuthorities().toArray()[0].toString();
		log.info("Role is ", role);
		
		String compact = Jwts.builder().setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))// token for 15 min
				.setId(role)
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
		return compact;
	}

	/**
	 * Validates the given JWT token. If valid, returns true. Else returns false
	 *
	 * @param token JWT token to validate user
	 * @return If a valid token is passed, True is returned. Else false
	 */
	public Boolean validateToken(String token) {
		try {

			if (!token.substring(0, 7).equals("Bearer ")) {
				return false;
			}

			String token1 = token.substring(7);
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token1).getBody().getExpiration();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}