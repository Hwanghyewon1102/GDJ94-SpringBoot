package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.board.notice.NoticeDTO;
import com.winter.app.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	@Autowired
	private QnaDAO qnaDAO;
	@Autowired
	private QnaService qnaService;
	
	
	@GetMapping("list")
	public void list(Model model, Pager pager, Long page) throws Exception {
		List<QnaDTO> list = qnaService.list(pager); 
		model.addAttribute("list", list);
         model.addAttribute("pager", pager); 
	}
	
	@GetMapping("add")
	public void add(QnaDTO qnaDTO)throws Exception{
	}
	
	
	@PostMapping("add")
	public String add(QnaDTO qnaDTO, Model model)throws Exception{
		int result = qnaService.add(qnaDTO);
		
		if(result > 0) {
		}
		
		model.addAttribute("path", "./list");
		return "redirect:./list";
	}
	
	
}
