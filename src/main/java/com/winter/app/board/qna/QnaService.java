package com.winter.app.board.qna;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardService;
import com.winter.app.board.notice.NoticeDTO;
import com.winter.app.util.Pager;

@Service
public class QnaService implements BoardService{
	
	@Autowired
	private QnaDAO qnaDAO;
	
	public List<BoardDTO> list(Pager pager) throws Exception{
		Long totalCount = qnaDAO.count(pager);
		pager.pageing(totalCount);
		return qnaDAO.list(pager);
	}
	
	
	@Override
	public int add(BoardDTO boardDTO)throws Exception{
		int result = qnaDAO.add(boardDTO);
		qnaDAO.refUpdate(boardDTO);
		
		return result;
	}
	
	

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
