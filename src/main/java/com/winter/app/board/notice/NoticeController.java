package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winter.app.board.BoardDTO;
import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public void  list(Pager pager, Long page, Model model) throws Exception{
		
		List<BoardDTO> list = noticeService.list(pager);
	    model.addAttribute("list", list);
	    
	    model.addAttribute("pager", pager);
	}
	
	
	
	@GetMapping("add")
	public void add()throws Exception{
	}
	
	
	@PostMapping("add")
	public String add(NoticeDTO noticeDTO, Model model) throws Exception{
		int result = noticeService.add(noticeDTO);
		
		if(result > 0) {
			return "redirect:list";
			
		}
		return "redirect:list";
	}
	
	@GetMapping("detail")
	public String detail(BoardDTO boardDTO, Model model) throws Exception{
		boardDTO = noticeService.detail(boardDTO);
		
		model.addAttribute("detail", boardDTO);
		//null(조회실패시 처리)
		if(boardDTO == null) {
			return "redirect:list";
		}
		return "notice/detail";
		
	}
	
	
}
