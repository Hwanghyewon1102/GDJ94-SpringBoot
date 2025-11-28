package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardDTO;
import com.winter.app.util.Pager;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("list")
	public String list(Model model, Pager pager)throws Exception{
		
		 List<BoardDTO> list = productService.list(pager);
		 model.addAttribute("list", list);
		
		return "product/list";
	}
	
	
	
}
