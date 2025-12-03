package com.winter.app.board.qna;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; // ⭕ 이거!
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardFileDTO;
import com.winter.app.board.BoardService;
import com.winter.app.board.notice.NoticeFileDTO;
import com.winter.app.files.FileManager;
import com.winter.app.util.Pager;

@Service
public class QnaService implements BoardService {

    @Autowired
    private QnaDAO qnaDAO;
    
    @Autowired
    private FileManager fileManager;

    @Value("${app.upload.qna}")  
    private String uploadPath;

    @Override
    public List<BoardDTO> list(Pager pager) throws Exception {
        Long totalCount = qnaDAO.count(pager);
        pager.pageing(totalCount);
        return qnaDAO.list(pager);
    }

    @Override
    public int add(BoardDTO boardDTO, MultipartFile[] attach) throws Exception {
    	int result = qnaDAO.add(boardDTO);
		System.out.println("boardNum = " + boardDTO.getBoardNum());
    	qnaDAO.refUpdate(boardDTO);
		System.out.println("boardNum = " + boardDTO.getBoardNum());
    	if (attach == null) {
				return result;
		}
    		
    	File file = new File(uploadPath);
    		
        for (MultipartFile f : attach) {
        	if(f == null || f.isEmpty()) {
				continue;
			}
			String fileName = fileManager.fileSave(file, f);

			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setFileName(fileName);
			boardFileDTO.setFileOrigin(f.getOriginalFilename());
			boardFileDTO.setBoardNum(boardDTO.getBoardNum());
			qnaDAO.fileAdd(boardFileDTO);
        }
        
        return result;

    }

    @Override
    public BoardDTO detail(BoardDTO boardDTO) throws Exception {
        return qnaDAO.detail(boardDTO);
    }

    @Override
    public int update(BoardDTO boardDTO) throws Exception {
        return qnaDAO.update(boardDTO);
    }

    @Override
    public int delete(BoardDTO boardDTO) throws Exception {
        return qnaDAO.delete(boardDTO);
    }

    public int reply(QnaDTO qnaDTO) throws Exception {
    	QnaDTO parent = (QnaDTO) qnaDAO.detail(qnaDTO); 

        int result = qnaDAO.stepUpdate(parent);

        qnaDTO.setBoardRef(parent.getBoardRef());
        qnaDTO.setBoardStep(parent.getBoardStep() + 1);
        qnaDTO.setBoardDepth(parent.getBoardDepth() + 1);

        qnaDAO.add(qnaDTO);

        return result;
    }

	@Override
	public BoardFileDTO fileDetail(BoardFileDTO boardFileDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.fileDetail(boardFileDTO);
	}
}
