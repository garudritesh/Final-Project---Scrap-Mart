package com.app.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.ScrapMaterial;
import com.app.entity.User;
import com.app.entity.Cart;
import com.app.repo.UserRepository;
import com.app.repo.ScrapRepository;
import com.app.repo.CartRepository;

@Service
public class CartServiceImple {
	@Autowired
	private CartRepository cartRepository;
	
	public Cart AddToCartMaterial(Cart cart) {
		return cartRepository.save(cart);
	}
	
	public List<Cart> FindAllCart(User user) {
		return cartRepository.findByUser(user);
	}
	
	public void deletecartMaterial(int id) {
		cartRepository.deleteById(id);
	}
}
