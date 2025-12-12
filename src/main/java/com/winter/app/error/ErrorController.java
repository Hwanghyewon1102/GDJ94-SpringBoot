package com.winter.app.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

	
	// 예외를 전역 처리하는 예외 Controller
	@ExceptionHandler(exception =  NullPointerException.class)
	public String e1(Model model) {
		return"error/error_page";
		
		
		
	}
	
//	@ExceptionHandler(NullPointerException.class)
//	public String exc2(Model model) {
//		
//		return "error/error_page";
//	}
	
	
	@ExceptionHandler(Throwable.class)
	public String exc3(Model model) {
		
		return "error/error_page";

	}
}
