package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Order;
import com.app.entity.ScrapMaterial;
import com.app.entity.User;
import com.app.repo.OrderRepository;
import com.app.repo.UserRepository;

@Service
public class AdminServiceImple {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public User fetchByUserMail(String tempMail) {
		
		return userRepository.findByUserMail(tempMail);
		 
	}
	
	public List<User> fetchAllUsers(String users) {
		
		return userRepository.findAllUsers(users);
		 
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
	
	public int deleteDeliveryPartner(int userId){
		System.out.println("Working service");
		return userRepository.removeByUserId(userId);
	}
	
	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}
	
}
