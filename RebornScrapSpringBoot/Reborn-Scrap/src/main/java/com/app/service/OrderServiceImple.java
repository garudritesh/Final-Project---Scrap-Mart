package com.app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Cart;
import com.app.entity.Order;
import com.app.entity.ScrapMaterial;
import com.app.entity.User;
import com.app.repo.UserRepository;
import com.app.repo.CartRepository;
import com.app.repo.OrderRepository;
import com.app.repo.ScrapRepository;

@Service
public class OrderServiceImple {

	@Autowired
	private OrderRepository orderRepository;
	
	public void AddToOrder(Order order) {
		 orderRepository.save(order);
	}
	
	public List<Order> FindAllOrder(User user) {
		return orderRepository.findByUser(user);
	}	
	
	public List<Order> FindAllOrderList(String name) {
		return orderRepository.findBystatus(name);
	}
	
	public List<Order> FindAllOrderList2(String name, String name2) {
		return orderRepository.findBystatus2(name, name2);
	}
	
	public void deleteorder(int id) {
		orderRepository.deleteById(id);
	}
	
	public void updateOrder(int id) {
		orderRepository.confirmOrder(id);
	}
	
	public Order fetchByOrderId(int id) {
		return orderRepository.findByOrderId(id);
	}
	
}
