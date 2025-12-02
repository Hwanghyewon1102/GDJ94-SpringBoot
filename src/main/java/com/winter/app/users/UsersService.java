package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.files.FileManager;

@Service
public class UsersService {

	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.users}")
	private String uploadPath;

	 public int add(UsersDTO usersDTO, MultipartFile profile) throws Exception {

	        int result = usersDAO.add(usersDTO);


	        if (profile != null && !profile.isEmpty()) {

	            File file = new File(uploadPath);

	            String fileName = fileManager.fileSave(file, profile);

	            UserFileDTO usersFileDTO = new UserFileDTO();
	            usersFileDTO.setUsername(usersDTO.getUsername());
	            usersFileDTO.setFileName(fileName);
	            usersFileDTO.setFileOrigin(profile.getOriginalFilename());

	            usersDAO.fileAdd(usersFileDTO);
	        }

	        return result;
	    }







	
}
