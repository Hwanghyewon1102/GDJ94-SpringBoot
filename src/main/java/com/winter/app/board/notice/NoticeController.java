package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardFileDTO;
import com.winter.app.files.FileManager;
import com.winter.app.util.Pager;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {

    private final FileManager fileManager;

	@Autowired
	private NoticeService noticeService;
	
	@Value("${category.board.notice}")
	private String category;

    NoticeController(FileManager fileManager) {
        this.fileManager = fileManager;
    }
	
	@ModelAttribute("category")
	public String getCategory() {
		return this.category;		
	}
	
	@GetMapping("list")
	public String list(Pager pager, Long page, Model model) throws Exception{
		
		List<BoardDTO> list = noticeService.list(pager);
	    model.addAttribute("list", list);
	    
	    model.addAttribute("pager", pager);
	    
	    return "board/list";
	}
	
	
	
	@GetMapping("add")
	public String add(@ModelAttribute("dto") NoticeDTO noticeDTO, Model model )throws Exception{
		model.addAttribute("sub", "Add");
		return "board/add";
	}
	
	
	@PostMapping("add")
	public String add(@ModelAttribute("dto") @Valid NoticeDTO noticeDTO, BindingResult bindingResult, Model model, MultipartFile[] attach) throws Exception{
		
		
		if(bindingResult.hasErrors()) {
			return "board/add";
		}
		 int result = noticeService.add(noticeDTO, attach);
		
//		if(result > 0) {
//			return "redirect:list";
//			
//		}
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
		return "board/detail";
		
	}
	
	@GetMapping("update")
	public String update(BoardDTO boardDTO, Model model)throws Exception{
		boardDTO = noticeService.detail(boardDTO);
		model.addAttribute("dto", boardDTO);
		model.addAttribute("sub", "Update");
		return "board/add";
	}
	
	@PostMapping("update")
	public String update(BoardDTO boardDTO)throws Exception{
		int result = noticeService.update(boardDTO);
		return "redirect:./list";
	}
	
	@PostMapping("delete")
	public String delete(BoardDTO boardDTO) throws Exception{
		int result = noticeService.delete(boardDTO);
		
		return "redirect:./list";
	}
	
	@GetMapping("fileDown")
	public String fileDown(BoardFileDTO boardFileDTO, Model model) throws Exception{
		boardFileDTO = noticeService.fileDetail(boardFileDTO);
		model.addAttribute("file", boardFileDTO);
		
		return "fileDownView";
	}
}
