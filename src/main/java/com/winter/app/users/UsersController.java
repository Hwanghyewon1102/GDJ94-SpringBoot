package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/users/*")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("register")
	public String add(Model model) throws Exception{
		
		return "users/register";
	}
	
	
	
	@PostMapping("register")
	public String add(UsersDTO usersDTO ,Model model, MultipartFile profile) throws Exception{
		int result = usersService.add(usersDTO, profile);
		
		if(result < 0) {
			return "redirect:/";
			
		}
		return "redirect:/";
	}
	
	
	
	
	@GetMapping("login")
	public void login()throws Exception{
		
	}
	
	@PostMapping("login")
	public String login(UsersDTO usersDTO, HttpSession session)throws Exception{
		
		usersDTO = usersService.detail(usersDTO);
		
		session.setAttribute("user", usersDTO);
		
		return "redirect:/";
	}
	
	
//	@GetMapping("mypage")
//	public String detail(String username,Model model)throws Exception{
//		UsersDTO usersDTO = usersService.detail(username);
//		model.addAttribute("user", usersDTO);
//	}
}
