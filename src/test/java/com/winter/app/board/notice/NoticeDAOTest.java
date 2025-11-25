package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class NoticeDAOTest {
	@Autowired
	private NoticeDAO noticeDAO;
	
	// @Test
	void testDetail() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(1L);
		
		noticeDTO = noticeDAO.detail(noticeDTO);
		assertNotNull(noticeDTO);
	}
	
	// @Test
	void testList() throws Exception{
		List<NoticeDTO> ar = noticeDAO.list();
		assertNotEquals(0, ar.size());
	}
	
	// @Test
	void testAdd() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardTitle("공지사항입니다~~~");
		noticeDTO.setBoardWriter("후임관리자!");
		noticeDTO.setBoardContents("공지공지공공지");
		
		int result = noticeDAO.add(noticeDTO);
		assertEquals(1, result);
		
	}
	
	// @Test
	void testDelete() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(2L);
		int result = noticeDAO.delete(noticeDTO);
		assertEquals(1, result);
	}
	
	@Test
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
