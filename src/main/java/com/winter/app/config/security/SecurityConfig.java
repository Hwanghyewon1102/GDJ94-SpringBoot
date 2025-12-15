package com.winter.app.config.security;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.winter.app.config.security.jwt.JwtLoginFilter;
import com.winter.app.config.security.jwt.JwtTokenManager;
import com.winter.app.users.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
    private final LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private LoginFailHandler loginFailHandler;
	
	@Autowired
	private Logout logout;
	
	@Autowired
	private LogoutSucess logoutSucess;
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	private JwtTokenManager jwtTokenManager;

    SecurityConfig(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }

	// 정적자원들을 Security에서 제외
	@Bean
	WebSecurityCustomizer customizer() {
		return web -> {
			web
			.ignoring()
				.requestMatchers("/css/**")
				.requestMatchers("/images/**", "/img/**", "/js/**", "/vendor/**");
				
		};
	}
	
		
		// 인증과 인가에 관한 설정
		@Bean
		SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		    security
		        .cors(cors -> cors.disable())
		        .csrf(csrf -> csrf.disable())
		        
		    	// 인가(권한)에 관한 설정
		        .authorizeHttpRequests(auth -> auth
		            .requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
		            .requestMatchers("/product/add", "/product/update", "/product/delete").hasAnyRole("ADMIN", "MANAGER")
		            .requestMatchers("/product/**").authenticated()
		            .requestMatchers("/users/mypage", "/users/update", "/users/logout").authenticated()
		            .anyRequest().permitAll()
		        )
		        
		        // 로그인 form과 그외 관련 설정

		        .formLogin((form) -> {
			        // front 분리
			         form.disable();//폼 비활성화
		        })

		        .logout(logout -> logout
		            .logoutUrl("/users/logout")   
		            // .logoutSuccessUrl("/")
		            .addLogoutHandler(this.logout)
		            //.logoutSuccessHandler(logoutSucess)
		            .invalidateHttpSession(true)
		            .deleteCookies("JSESSIONID")
		            .deleteCookies("remember-me")
		            .deleteCookies("access-token", "refresh-token")
		        )
		        
		        
		        .rememberMe(remember -> {
		        	remember
		        		.rememberMeParameter("rememberme")
		        		.tokenValiditySeconds(60)
		        		.key("rememberkey")
		        		.userDetailsService(userDetailServiceImpl)
		        		.authenticationSuccessHandler(loginSuccessHandler)
		        		.useSecureCookie(false)
		        		;
		        })
		        
		        .sessionManagement(session -> {
		        	session
		        		.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		        		
		        		
		        })
		        .httpBasic((h) -> {
		        	h.disable();
		        })
		        
		        .addFilter(new JwtLoginFilter(jwtTokenManager))
		        
		        .oauth2Login(t -> {
		        	t.userInfoEndpoint((s) -> {
		        		s.userService(userDetailServiceImpl);
		        	});
		        	
		        })
		        
		        
		        ;


		    return security.build();
		}
		
		
		@Bean
		PasswordEncoder getPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
	}
