package com.winter.app.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class UserDTO  implements UserDetails, OAuth2User{
	
	
	
	
	@NotBlank(groups = {RegisterGroup.class})
	private String username;
	
	// @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()])(?=\\S+$).{8,12}$", message = "특수, 대소문자, 숫자")
	@NotBlank(groups = {RegisterGroup.class, PasswordGroup.class})
	private String password;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> list = new ArrayList<>();
		
		for(int i = 0; i < roleDTOs.size(); i++) {
			GrantedAuthority g = new SimpleGrantedAuthority(roleDTOs.get(i).getRoleName());
					list.add(g);
		}
		return list;
	}




	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		// 사용자 계정의 유효 기간이 만료 되었습니다.
		// AccountExpiredException
		return this.accountNonExpired;
	}




	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		// 사용자 계정이 잠겨 있습니다.
		//LockedException
		return this.accountNonLocked;
	}




	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		// 자격 증명 유효 기간이 만료되었습니다.
		// CredentialsExpiredException
		
		return this.credentialsNonExpired;
	}




	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		// 유효하지 않은 사용자입니다.
		// DisabledException
		return this.enabled;
	}




	private String passwordCheck;
	private String newPassword;
	@NotBlank(groups = {RegisterGroup.class, UpdateGroup.class})
	private String name;
	
	@Email
	@NotBlank(groups = {RegisterGroup.class, UpdateGroup.class})
	private String email;
	
	@Pattern(regexp = "^\\d+$", message = "숫자만 입력 가능합니다.")
	private String phone;
	
	@Past(groups = {RegisterGroup.class, UpdateGroup.class})
	private LocalDate birth;
	
	
	
	
	private UserFileDTO userFileDTO;
	
	
	private List<RoleDTO> roleDTOs;
	
	
	
	// UserDetail
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

	
	//OAuth2User
	private Map<String, Object> attributes;
	private String sns;
	
	
	
	
}