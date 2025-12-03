package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardFileDTO;
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
	
	@Value("${category.board.qna}")
	private String category;
	
	@ModelAttribute("category")
	public String getCategory() {
		return this.category;		
	}
	
	@GetMapping("list")
	public String list(Model model, Pager pager, Long page) throws Exception {
		List<BoardDTO> list = qnaService.list(pager); 
		model.addAttribute("list", list);
        model.addAttribute("pager", pager);
         
        return "board/list";
	}
	
	@GetMapping("add")
	public String add(Model model)throws Exception{
		model.addAttribute("sub", "Add");
		return "board/add";
	}
	
	
	@PostMapping("add")
	public String add(QnaDTO qnaDTO, Model model, MultipartFile[] attach)throws Exception{
		qnaDTO.setBoardRef(0L);
		qnaDTO.setBoardStep(0L);
		qnaDTO.setBoardDepth(0L);
		int result = qnaService.add(qnaDTO ,attach);
		
		if(result > 0) {
		}
		
		model.addAttribute("path", "./list");
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public String detail(BoardDTO boardDTO, Model model) throws Exception{
		boardDTO = qnaService.detail(boardDTO);
		model.addAttribute("detail", boardDTO);
		
		if(boardDTO == null) {
			return "redirect:./list";
			
		}
		return "board/detail";
	}
	
	@GetMapping("reply")
	public String reply(QnaDTO qnaDTO, Model model) throws Exception{
		model.addAttribute("dto", qnaDTO);
		return "board/add";
	}
	
	@PostMapping("reply")
	public String reply(QnaDTO qnaDTO)throws Exception{
		int result = qnaService.reply(qnaDTO);
		
		return "redirect:./list";
	}
	
	
	@GetMapping("update")
	public String update(BoardDTO boardDTO, Model model) throws Exception{
		boardDTO = qnaService.detail(boardDTO);
		model.addAttribute("dto", boardDTO);
		model.addAttribute("sub", "Update");
		return "board/add";
	}
	
	@PostMapping("update")
	public String update(BoardDTO boardDTO )throws Exception{
		int result = qnaService.update(boardDTO);
		return "redirect:./list";
	}
	
	@PostMapping("delete")
	public String delete(BoardDTO boardDTO) throws Exception{
		int result = qnaService.delete(boardDTO);
		
		return "redirect:./list";
	}
	
	
	@GetMapping("fileDown")
	public void fileDown(BoardFileDTO boardFileDTO) throws Exception{
		boardFileDTO = qnaService.fileDetail(boardFileDTO);
	}
	
}
