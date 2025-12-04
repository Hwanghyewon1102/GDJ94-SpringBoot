package com.winter.app.users;

import java.time.LocalDate;

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
public class UserDTO {
	
	@NotBlank(groups = {RegisterGroup.class})
	private String username;
	
	// @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()])(?=\\S+$).{8,12}$", message = "특수, 대소문자, 숫자")
	@NotBlank(groups = {RegisterGroup.class, PasswordGroup.class})
	private String password;
	
	
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

}