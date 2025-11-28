package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.util.Pager;
@SpringBootTest
class NoticeDAOTest {
	@Autowired
	private NoticeDAO noticeDAO;
	
	// @Test
//	void testDetail() throws Exception{
//		NoticeDTO noticeDTO = new NoticeDTO();
//		noticeDTO.setBoardNum(1L);
//		
//		noticeDTO = noticeDAO.detail(noticeDTO);
//		assertNotNull(noticeDTO);
//	}
	
	// @Test
	//public List<NoticeDTO> list(Long page, Pager pager) throws Exception{
		// page*pager.getCountNum()-pager.getCountNum()
		// (page-1)*pager.getCountNum()
		// (page-1)*10
		// 1 = 0    0
		// 2 = 10   5
		// 3 = 20   10
		//pager.setCountNum(10L);
		//Long s = (page-1)*pager.getCountNum();
		//pager.setStartNum(s);
		//return noticeDAO.list(pager);
	// }
	
	 @Test
	void testAdd() throws Exception{
		for(int i=0; i<120; i++) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setBoardTitle("title"+i);
			noticeDTO.setBoardWriter("writer"+i);
			noticeDTO.setBoardContents("contents"+i);
			
			noticeDAO.add(noticeDTO);
			if(i%10==0){
				Thread.sleep(500);
			}
		}
		
	}
	
	// @Test
	void testDelete() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(2L);
		int result = noticeDAO.delete(noticeDTO);
		assertEquals(1, result);
	}
	
	// @Test
	void testUpdate() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardTitle("업데이트");
		noticeDTO.setBoardWriter("안돼!");
		noticeDTO.setBoardContents("업데이트할거야!");
		noticeDTO.setBoardNum(3L);
		
		int result = noticeDAO.update(noticeDTO);
		assertEquals(1, result);
	}

}
