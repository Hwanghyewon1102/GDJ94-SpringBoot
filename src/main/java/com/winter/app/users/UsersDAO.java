package com.winter.app.users;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileDTO;

@Mapper
public interface UsersDAO {
	public int add(UsersDTO usersDTO)throws Exception;

	public int fileAdd(UserFileDTO userFileDTO) throws Exception;
	public int fileDelete(UsersDTO usersDTO)throws Exception;
}
