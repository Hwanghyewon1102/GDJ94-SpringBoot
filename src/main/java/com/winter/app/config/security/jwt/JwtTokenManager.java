package com.winter.app.config.security.jwt;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.winter.app.users.UserDAO;
import com.winter.app.users.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {
	
	@Value("${jwt.accessValidToken}")
	private Long accessValidToken;
	
	@Value("${jwt.refreshValidToken}")
	private Long refreshValidToken;
	
	@Value("${jwt.issuer}")
	private String issuer;
	
	private SecretKey key;
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Value("${jwt.secretKey}")
	private String secretKey;
	@PostConstruct
	public void init() {
//		String k = Base64.getEncoder().encodeToString(secretKey.getBytes());
//				key = Keys.hmacShaKeyFor(k.getBytes());
		
		key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	
	public String makeAccessToken(Authentication authentication) {
		return this.createToken(authentication, accessValidToken);
	}
	
	public String makeRefreshToken(Authentication authentication) {
		return this.createToken(authentication, refreshValidToken);
	}
	
	private String createToken(Authentication authentication, Long valid) {
		Date expirationDate = new Date(System.currentTimeMillis() + valid);
		
		return Jwts
				.builder()
				// 사용자의 ID
				.subject(authentication.getName())
				// 넣고싶은 다른 정보들...
				.claim("roles", authentication.getAuthorities().toString())
				// Token 생성시간
				.issuedAt(new Date())
				// Token 유효기간
				//.expiration(new Date(System.currentTimeMillis()))
				.expiration(expirationDate)
				// 발급자
				.issuer(issuer)
				// 암호화 알고리즘 
				.signWith(key, Jwts.SIG.HS256)
				
				.compact()
				;
	}
	// Token 검증
	public Authentication getAuthenticationByToken(String token) throws Exception{
		
		// 검증
		Claims claims = 
				Jwts
					.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(token)
					.getPayload()					
					;
		
		// 검증 성공, 검증 실패시 Exception 발생
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(claims.getSubject());
		UserDetails userDetails = userDAO.detail(userDTO);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		return authentication;
	}
}



