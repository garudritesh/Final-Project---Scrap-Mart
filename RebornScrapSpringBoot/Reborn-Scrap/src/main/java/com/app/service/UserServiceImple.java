package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.ScrapMaterial;
import com.app.entity.User;
import com.app.repo.UserRepository;

@Service
public class UserServiceImple {
	
	@Autowired
	private UserRepository userRepository;
		
	public User fetchByUserMail(String tempMail) {
		
		return userRepository.findByUserMail(tempMail);
		 
	}
	
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}
	
	public User fetchByUserMailAndUserPassword(String tempMail,String tempPassword) {
		
		return userRepository.findByUserMailAndUserPassword(tempMail, tempPassword);
		
	}
	
	public User fetchByUserId(int id) {
		return userRepository.findByUserId(id);
	}
	
	
		
}

