package com.winter.app.config.security.jwt;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter{
	
	private JwtTokenManager jwtTokenManager;

	public JwtAuthenticationFilter(JwtTokenManager manager, AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.jwtTokenManager = manager;
	}
	
	// Token 검증
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Cookie [] cookies = request.getCookies();
		String token = "";
		if(cookies != null) {
			for (Cookie c: cookies) {
				if(c.getName().equals("access-token"));
				token = c.getValue();
				break;
				
			}
		}

		if(token != null && token.length() != 0) {
			try {
				Authentication authentication = this.jwtTokenManager.getAuthenticationByToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				// SecurityException || MalformedException ||SionatureException
				// ExpriredJwtException: 기간이 만료된 Token
				// ㅕㅜ녀ㅔㅔㅐㄳㄷ엊ㅅㄸㅌㅊ데샤ㅐㅜ: 지원되지 않는 token
				// IllegalArgumentException: 잘못된 Token
				System.out.println(e.getMessage());
				if (e instanceof ExpiredJwtException) {

					// Refreshtoken으로 AccessToken 생성
					// DB에서 조회 또는 저장소에서 가져오기
					for (Cookie c: cookies) {
						if(c.getName().equals("access-token"));
						token = c.getValue();
						break;
						
					}
				}
				
				// refresh token을 검증
				try {
					Authentication authentication = jwtTokenManager.getAuthenticationByToken(token);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					// access token 생성
					String newtoken = jwtTokenManager.makeAccessToken(authentication);
					Cookie c = new Cookie("access-token", newtoken);
					
					response.addCookie(c);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		
		chain.doFilter(request, response);
		
		
	}
	
}
