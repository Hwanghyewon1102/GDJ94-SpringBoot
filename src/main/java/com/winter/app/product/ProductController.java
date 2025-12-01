package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.util.Pager;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("list")
	public String list(Model model, Pager pager)throws Exception{
		
		 List<ProductDTO> list = productService.list(pager);
		 model.addAttribute("list", list);
		
	    model.addAttribute("pager", pager);

		return "product/list";
	}
	
	
	
	@GetMapping("detail")
	public String detail(ProductDTO productDTO, Model model)throws Exception{
		ProductDTO detail = productService.detail(productDTO);
		model.addAttribute("detail", detail);
		
	 return "product/detail";
	}
	
	
	
	@GetMapping("add")
	public String add( Model model )throws Exception{
		return "product/add";
	}
	
	@PostMapping("add")
	public String add(ProductDTO productDTO)throws Exception{
		int result = productService.add(productDTO);
		
		if(result > 0) {
			return"redirect:./list";
			
		}
		return"redirect:./list";
	}
	
	@GetMapping("update")
	public String update(Model model, ProductDTO productDTO)throws Exception{
		ProductDTO dto = productService.detail(productDTO);
	    model.addAttribute("dto", dto);
		return "product/add";
	}
	
	@PostMapping("update")
	public String update(ProductDTO productDTO, Model model)throws Exception{
		int result = productService.update(productDTO);
		

		if(result > 0) {
			return "redirect:./detail?productsNum=" + productDTO.getProductsNum();			
			
		}
		return"redirect:./list";
	}
	
	@PostMapping("delete")
	public String delete(ProductDTO productDTO)throws Exception {
		int result = productService.delete(productDTO);
		return"redirect:./list";
	}
	}
