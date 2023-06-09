package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.IAdminDao;
import com.app.dao.ILoginDao;
import com.app.dto.ChangePassword;
import com.app.pojos.Login;

@Service
@Transactional
public class AdminService {
	@Autowired
	private IAdminDao adminDao;
	@Autowired
	private ILoginDao loginDao;
	
	public void changePassword(ChangePassword obj) {
		if(obj.getCnfPassword().equals(obj.getNewPassword())) {
			String encodedPassword=	new BCryptPasswordEncoder().encode(obj.getCnfPassword());
			Login login=loginDao.findByUsername(obj.getUsername());
			login.setPassword(encodedPassword);
		}
		else
			throw new ResourceNotFoundException("no user registered with   "+obj.getUsername()+"   username");
	}

}
