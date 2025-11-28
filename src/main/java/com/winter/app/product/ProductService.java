package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardService;
import com.winter.app.util.Pager;

@Service
public class ProductService implements BoardService{
	
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		
		return productDAO.list(pager);
	}

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		return null;
	}

	@Override
	public int add(BoardDTO boardDTO) throws Exception {
		return 0;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return 0;
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		return 0;
	}
	
}
