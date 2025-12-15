package com.winter.app.config.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter{
	
	private JwtTokenManager tokenManager;
	
	
	

	public JwtLoginFilter(JwtTokenManager tokenManager) {
		this.setFilterProcessesUrl("/users/loginProcess");
		this.tokenManager=tokenManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) // 로그인을 시도할 때 이용하는 메서드
			throws AuthenticationException {
		
		System.out.println("로그인 시도");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		// UserDetailService의 loadUserByusername 메서드를 호출하고
		// 패스워드가 일치하는지 판별하고 Authentication 객체를 
		// SecurityContextHolder에 넣어줌
		
		return super.attemptAuthentication(request, response);
	}
	
	@Override // 성공하면 메서드 실행
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = tokenManager.makeAccessToken(authResult);
		System.out.println(token);
	}
	
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
	}
}
