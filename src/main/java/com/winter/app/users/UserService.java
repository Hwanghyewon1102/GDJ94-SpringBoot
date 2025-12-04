package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.files.FileManager;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.user}")
	private String uploadPath;
	
	public boolean getError(UserDTO userDTO, BindingResult bindingResult) throws Exception{
		// check: true -> 검증 실패, error 존재
		// check: false -> 검증 성공, error 미존재
		// 1. annotation 검증 결과
		boolean check = bindingResult.hasErrors();
		
		
		// 2. password 일치 하는지 검증
		if(!userDTO.getPassword().equals(userDTO.getPasswordCheck())) {
			check=true;
//			bindingResult.rejectValue("멤버변수 명", "properties의 키 값");
			bindingResult.rejectValue("passwordCheck", "user.password.equal");
		}
		
		// 3. id 중복 체크
		if(userDTO.getUsername() != null) {
			UserDTO idcheck = userDAO.detail(userDTO);
			if(idcheck != null) {
				check = true;
				bindingResult.rejectValue("username", "user.id.equal");
			}
		}
		if (userDTO.getPassword() != null && userDTO.getUsername() != null) {
	        UserDTO db = userDAO.detail(userDTO); 
	        
	        if (db != null) {
	            String OldPassword = userDTO.getPassword().trim(); 
	            String dbPassword = db.getPassword().trim();
	            
	            if (!OldPassword.equals(dbPassword)) {
	                check = true;
	                bindingResult.rejectValue("password", "user.oldpassword.notmatch"); 
	            }
	        }
	    }
		return check;
			
	}
	
	
	
	
	public int register(UserDTO userDTO, MultipartFile profile)throws Exception{
		int result=0;
		
		result = userDAO.register(userDTO);
		
		if(profile == null || profile.isEmpty()) {
			return result;
		}
		
		File file = new File(uploadPath);
		
		String fileName = fileManager.fileSave(file, profile);
		
		UserFileDTO userFileDTO = new UserFileDTO();
		userFileDTO.setUsername(userDTO.getUsername());
		userFileDTO.setFileName(fileName);
		userFileDTO.setFileOrigin(profile.getOriginalFilename());
		
		userDAO.userFileAdd(userFileDTO);
		
		return result;
	}
	public UserDTO detail(UserDTO userDTO)throws Exception{
		UserDTO loginDTO = userDAO.detail(userDTO);
		
		if(loginDTO != null) {
			if(loginDTO.getPassword().equals(userDTO.getPassword())) {
				return loginDTO;
			}else {
				loginDTO = null;
			}
		}
		
		
		return loginDTO;
	}
	
	
	public int update(UserDTO userDTO)throws Exception{
		return userDAO.update(userDTO);
	}
	
	public int change(UserDTO userDTO) throws Exception {
	    
	    UserDTO dbDTO = userDAO.detail(userDTO); 
	    
	    if (dbDTO == null) {
	        throw new Exception("사용자 정보를 찾을 수 없습니다.");
	    }

	    String inputOldPassword = userDTO.getPassword(); 
	    String dbPassword = dbDTO.getPassword();      
	    
	    if (!inputOldPassword.trim().equals(dbPassword.trim())) {
	    }
	    
	    int result = userDAO.change(userDTO);
	    
	    return result;
	}
	
	
}