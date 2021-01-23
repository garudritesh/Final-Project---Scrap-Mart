package com.app.cntr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import com.app.entity.User;
import com.app.exception.UserServiceException;
import com.app.repo.CartRepository;
import com.app.repo.OrderRepository;
import com.app.entity.Cart;
import com.app.entity.Order;
import com.app.entity.ScrapMaterial;
import com.app.service.UserServiceImple;
import com.app.service.ScrapServiceImple;
import com.app.service.CartServiceImple;
import com.app.service.OrderServiceImple;
import com.app.service.AdminServiceImple;

@RestController
public class AdminController {

	@Autowired
	private UserServiceImple userServiceImple;
	
	@Autowired
	private ScrapServiceImple scrapServiceImple;
	
	@Autowired
	private CartServiceImple cartServiceImple;
	
	@Autowired
	private OrderServiceImple orderServiceImple;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	public String encoder(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		return encodedPassword;	
	}
	
	public boolean decoder(String password, String encoded) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(password, encoded);
		return isPasswordMatch;
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/Confirmorder")
	public void Confirmorder(@RequestBody int id) throws Exception {

		 orderServiceImple.updateOrder(id);		
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/AllOrderList1")
	public List<Order> allOrdersList() throws Exception {
		System.out.println("hello");
		List<Order> order = orderServiceImple.FindAllOrderList("No");
	
		return order;
			
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/AllOrderList2")
	public List<Order> allOrdersList2() throws Exception {
		
		List<Order> order = orderServiceImple.FindAllOrderList2("Yes","Delivered");
	
		return order;
			
	}
	
	
	@Autowired
	private AdminServiceImple adminServiceImple;
	
	@CrossOrigin(origins = "*")
	@PostMapping("/registerDeliveryPartner")
	public User registerUser1(@RequestBody User user) throws Exception {
		
		String tempMail = user.getUserMail();
		
		if(tempMail != null && !"".equals(tempMail)) {
			
			User userObj = adminServiceImple.fetchByUserMail(tempMail);
			
			if(userObj != null) {
				
				throw new UserServiceException("Delivery partner with "+tempMail+" already exist");
			}
		}
		
		User userObj = null;
		if(user.getUserType() == null) {
			user.setUserType("Delivery partner");
		}
		
		String encoded = encoder(user.getUserPassword());
		user.setUserPassword(encoded);
		
		userObj = adminServiceImple.saveUser(user);
		
		return userObj;
		
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getusers/{users}")
	public List<User> getAllUsers(@PathVariable String users) throws Exception {
		
		return adminServiceImple.fetchAllUsers(users);
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/deleteDeliveryPartner/{userId}")
	public int deleteDeliveryPartner(@PathVariable int userId) throws Exception {	
		System.out.println("Working controller");
		return adminServiceImple.deleteDeliveryPartner(userId);
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/updateOrder")
	public Order updateScrap(@RequestBody Order order) throws Exception {
		System.out.println(order+"hello");

		return adminServiceImple.updateOrder(order);
			
	}
}
