package com.app.cntr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.app.entity.User;
import com.app.exception.UserServiceException;
import com.app.repo.CartRepository;
import com.app.repo.OrderRepository;
import com.app.entity.Cart;
import com.app.entity.ContactUs;
import com.app.entity.Order;
import com.app.entity.ScrapMaterial;
import com.app.service.UserServiceImple;
import com.app.service.ScrapServiceImple;
import com.app.service.SendEmailService;
import com.app.service.AdminServiceImple;
import com.app.service.CartServiceImple;
import com.app.service.ContactUsImple;
import com.app.service.OrderServiceImple;


@RestController
public class UserController {
	
	private String mailId;
	
	@Autowired
	private UserServiceImple userServiceImple;
	
	@Autowired
	private AdminServiceImple adminServiceImple;
	
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
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired
	private ContactUsImple contactUsImple;
	
	
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
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}
	
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) throws Exception {
		
		String tempMail = user.getUserMail();
		
		if(tempMail != null && !"".equals(tempMail)) {
			
			User userObj = userServiceImple.fetchByUserMail(tempMail);
			
			if(userObj != null) {
				
				throw new UserServiceException("user with "+tempMail+" is already exist");
			}
		}
		
		User userObj = null;
		if(user.getUserType() == null) {
			user.setUserType("user");
		}
		
		String encoded = encoder(user.getUserPassword());
		user.setUserPassword(encoded);
		
		userObj = userServiceImple.saveUser(user);
		
		return userObj;
		
	}
	
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("login")
	public User loginUser(@RequestBody User user) throws Exception {
		
		String tempMail = user.getUserMail();
		String tempPass = user.getUserPassword();
		// session.setAttribute("username", user);
		User userObj = null;
		
		if(tempMail != null && tempPass !=null) {
			//tempPass = encoder(user.getUserPassword());
			//userObj = userServiceImple.fetchByUserMailAndUserPassword(tempMail, tempPass);
			 
			 
			userObj = userServiceImple.fetchByUserMail(tempMail);
			boolean match = decoder(tempPass,userObj.getUserPassword());
			if(!match){
				throw new UserServiceException("Bad Credentials");
			}
			
		}
		
		if(userObj == null) {
			throw new UserServiceException("Bad Credentials");
		}
		
		return userObj;
		
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/sendmail")
	public void sendEmailForgotPass(@RequestBody User user) throws Exception {
		
		User userObj = userServiceImple.fetchByUserMail(user.getUserMail());
		
		String name=userObj.getUserName();
		
		this.mailId = user.getUserMail();
		
		if(this.mailId != null ) {
		sendEmailService.sendEmail(mailId,"Hello "+name+"\n\nPlease click below to reset your password for Reborn Scrap"  +"\n\nhttp://localhost:4200/getpassword"+"\n\nThank You", "Reborn Scrap - Reset Forgotten Password" + 
				"");
		                  }
		
		
		if(this.mailId == null) {
			throw new UserServiceException("Bad Credentials");
		}
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/setpassword")
	public User setPassword(@RequestBody User user) throws Exception {
		System.out.println(user+"hello");
		User userObj = userServiceImple.fetchByUserMail(this.mailId);
		
		String encoded = encoder(user.getUserPassword());
		userObj.setUserPassword(encoded);
	
		return userServiceImple.saveUser(userObj);
			
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getUser/{id}")
	public User GetUser(@PathVariable int id) throws Exception{
		User user = userServiceImple.fetchByUserId(id);
		if(user == null) {
			throw new UserServiceException("Bad Request");
		}
		return user;
	}
	
	
	
	@CrossOrigin(origins = "*")
	@PutMapping("/UpdateUser")
	public User updaterUser(@RequestBody User user) throws Exception {
		System.out.println(user+"hello");
		int id = user.getUserId();
		User userObj = userServiceImple.fetchByUserId(id);
		
	userObj.setUserName(user.getUserName());
	userObj.setUserMail(user.getUserMail());
    userObj.setUserPhoneNo(user.getUserPhoneNo());
	//userObj.setUserPassword(user.getUserPassword());
	userObj.setUserType(user.getUserType());
	userObj.setAddressStreet(user.getAddressStreet());
	userObj.setAddressStreet2(user.getAddressStreet2());
	userObj.setCity(user.getCity());
	userObj.setState(user.getState());
	userObj.setZipcode(user.getZipcode());
	
		return userServiceImple.saveUser(userObj);
			
	}
	

	
	@CrossOrigin(origins = "*")
	@PostMapping("/AddScrap")
	public ScrapMaterial addScrap(@RequestBody ScrapMaterial scrap) throws Exception {
		System.out.println(scrap+"hello");

		return scrapServiceImple.saveScrapMaterial(scrap);
			
	}
	
	

	@CrossOrigin(origins = "*")
	@PostMapping("/AddScrapImage")           
	public int addScrapImage(@RequestParam(value="imageFile") MultipartFile file1,
			@RequestParam(value="materialName") String file2) throws Exception {
		//System.out.println("Entered controller");
		//System.out.println("getOriginalFilename: "+file1.getOriginalFilename());
		//System.out.println("getName: "+file1.getName());
		//System.out.println(file2.toString());
		//System.out.println(file1.getContentType());
		String str = file2.toString();
		int materialName = Integer.parseInt(str);
		byte[] scrapImage = compressBytes(file1.getBytes());
		scrapServiceImple.updateScrapImage(materialName, scrapImage);
		//return scrapServiceImple.saveScrapMaterial(scrap);
		return 1;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/AddSpecificationImage")           
	public int addSpecificationImage(@RequestParam(value="imageFile") MultipartFile file1,
			@RequestParam(value="materialName") String file2) throws Exception {
		String str = file2.toString();
		int materialName = Integer.parseInt(str);
		byte[] scrapImage = compressBytes(file1.getBytes());
		System.out.println("getInteger: "+ materialName);
		System.out.println("getName: "+file1.getName());
		System.out.println("getString: "+file2.toString());
		System.out.println(file2.toString());
		scrapServiceImple.updateSpecificationImage(scrapImage, materialName);
		return 1;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/AddToCart/{id}")
	public Cart addToCart(@RequestBody Cart cart,@PathVariable int id) throws Exception {
		System.out.println(cart+"hello");
		ScrapMaterial scrap = scrapServiceImple.fetchByScrapId(id);
		
		User user = cart.getUser();
		List<Cart> cart2 = cartRepository.findByUserAndScrapMaterial(user , scrap );
		
		if( cart2.isEmpty()) {
			 int newQuantity =  scrap.getQuantity() - cart.getQuantity();
			  scrap.setQuantity(newQuantity); 
			  ScrapMaterial scrap1 =   scrapServiceImple.updateScrapMaterial(scrap);
			int TotalPrice =   scrap.getPrice() *   cart.getQuantity();
			 cart.setTotal(TotalPrice);
			cart.setScrapMaterial(scrap1);
		return cartServiceImple.AddToCartMaterial(cart);
		}
		else if( ! cart2.isEmpty()){
			
			Cart cart3 = new Cart();
			cart2.get(0).setQuantity(cart2.get(0).getQuantity() + cart.getQuantity()) ;
			cart3 = cart2.get(0);
			 int newQuantity =  scrap.getQuantity() - cart.getQuantity();
			  scrap.setQuantity(newQuantity); 
			  ScrapMaterial scrap1 =   scrapServiceImple.updateScrapMaterial(scrap);
			return cartRepository.save(cart3);
		}
		
		return cart;
			
	}
	
	
	
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/updatescrap")
	public ScrapMaterial updateScrap(@RequestBody ScrapMaterial scrap) throws Exception {
		System.out.println(scrap+"hello");
		int id4 = scrap.getScrapId();
		ScrapMaterial scrap1 = scrapServiceImple.fetchByScrapId(id4);
		scrap1.setUser(scrap.getUser());
		scrap1.setMaterialSpecification(scrap.getMaterialSpecification());
		scrap1.setMaterialType(scrap.getMaterialType());
		scrap1.setShape(scrap.getShape());
		scrap1.setHeatTreatement(scrap.getHeatTreatement());
		scrap1.setLength(scrap.getLength());
		scrap1.setWidth(scrap.getWidth());
		scrap1.setThickness(scrap.getThickness());
		scrap1.setMaterialdecription(scrap.getMaterialdecription());
		scrap1.setQuantity(scrap.getQuantity());
		scrap1.setPrice(scrap.getPrice());
		scrap1.setScrapId(scrap.getScrapId());
		scrap1.setMaterialName(scrap.getMaterialName());

		return scrapServiceImple.updateScrapMaterial(scrap1);
			
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/deletescrap")
	public void deleteScrap(@RequestBody int id) throws Exception {
		
		ScrapMaterial scrap = scrapServiceImple.fetchByScrapId(id);
		
		 cartRepository.deleteByScrapMaterial(scrap);
		 orderRepository.deleteByScrapMaterial(scrap);
			
		 scrapServiceImple.deleteScrapMaterial(id);
			
			
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/deletecart")
	public void deletecart(@RequestBody int id) throws Exception {
	
		 cartServiceImple.deletecartMaterial(id);		
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/deleteorder")
	public void deleteorder(@RequestBody int id) throws Exception {
	Order order = orderRepository.findByOrderId(id);
	   int id3 = order.getScrapMaterial().getScrapId();
		ScrapMaterial scrap = scrapServiceImple.fetchByScrapId(id3);
		int q = scrap.getQuantity() + order.getOrderQuantity();
		scrap.setQuantity(q);
		ScrapMaterial scrap1 = scrapServiceImple.updateScrapMaterial(scrap);
		 orderServiceImple.deleteorder(id);		
	}
	
	

	
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/findScrap")
	public List<ScrapMaterial> findScrap(@RequestBody ScrapMaterial scrap) throws Exception {
		System.out.println(scrap+"hello");
		int lht = scrap.getLength();
		int wth = scrap.getWidth();
		int tth = scrap.getThickness();
		String spe = scrap.getShape();
		String MT = scrap.getMaterialType();
		return scrapServiceImple.FindMaterial(lht,wth,tth,spe,MT);
			
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/findScrapByMaterial")
	public List<ScrapMaterial> findScrapByMaterial(@RequestBody ScrapMaterial scrap) throws Exception {
		
		String MT = scrap.getMaterialType();
		return scrapServiceImple.FindMaterialByMaterialType(MT);
			
	}
	
//	@CrossOrigin(origins = "*")
//	@GetMapping("/viewscrap/{id}")
//	public ScrapMaterial viewscrap(@PathVariable int id) throws Exception{
//		ScrapMaterial scrap = scrapServiceImple.fetchByScrapId(id);
//		if(scrap == null) {
//			throw new Exception("Bad Request");
//		}
//		return scrap;
//	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/viewscrap/{id}")
	public ScrapMaterial viewscrap(@PathVariable int id) throws Exception{
		//ScrapMaterial scrap = scrapServiceImple.fetchByScrapId(id);
		final Optional<ScrapMaterial> retrievedImage = scrapServiceImple.getByScrapId(id);
		
		byte[] scrapImage = decompressBytes(retrievedImage.get().getScrapImage());
		byte[] specificationImage = decompressBytes(retrievedImage.get().getSpecificationImage());
		
		ScrapMaterial scrap = new ScrapMaterial();
		scrap.setUser(retrievedImage.get().getUser());
		scrap.setMaterialSpecification(retrievedImage.get().getMaterialSpecification());
		scrap.setMaterialType(retrievedImage.get().getMaterialType());
		scrap.setShape(retrievedImage.get().getShape());
		scrap.setHeatTreatement(retrievedImage.get().getHeatTreatement());
		scrap.setLength(retrievedImage.get().getLength());
		scrap.setWidth(retrievedImage.get().getWidth());
		scrap.setThickness(retrievedImage.get().getThickness());
		scrap.setMaterialdecription(retrievedImage.get().getMaterialdecription());
		scrap.setQuantity(retrievedImage.get().getQuantity());
		scrap.setPrice(retrievedImage.get().getPrice());
		scrap.setScrapId(retrievedImage.get().getScrapId());
		scrap.setMaterialName(retrievedImage.get().getMaterialName());
		scrap.setScrapImage(scrapImage); 
		scrap.setSpecificationImage(specificationImage);

		if(scrap == null) {
			throw new Exception("Bad Request");
		}
		return scrap;
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/viewstatus/{id}")
	public Order viewstatus(@PathVariable int id) throws Exception{
		Order order = orderServiceImple.fetchByOrderId(id);
		
		return order;
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/allscrap")
	public List<ScrapMaterial> allScrap(@RequestBody User user) throws Exception {
		
		return scrapServiceImple.FindAllScrap(user);
			
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/allCart")
	public List<Cart> allCart(@RequestBody User user) throws Exception {
		
		List<Cart> cart = cartServiceImple.FindAllCart(user);
        int alltotal=0;
    
//		List<ScrapMaterial> scrapList = new ArrayList<ScrapMaterial>(); 
        if( ! cart.isEmpty()) {
        
	for(int i=0; i< cart.size(); i++) {
	
		alltotal= alltotal + cart.get(i).getTotal();
			//cart.get(i).getScrapMaterial().setId(cart.get(i).getCartId());
			//scrapList.add(cart.get(i).getScrapMaterial()); 
		}
	cart.get(0).setGrandTotal(alltotal);
        }
    
		return cart;
			
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/OrderList")
	public List<Order> allOrders(@RequestBody User user) throws Exception {
	
		List<Order> order = orderServiceImple.FindAllOrder(user);
	
		return order;
			
	}
	


	
	@CrossOrigin(origins = "*")
	@PostMapping("/AddOrders")
	public void addToOrder(@RequestBody User user) throws Exception {
		
		List<Cart> cart = cartServiceImple.FindAllCart(user);
      
       // List<Order> orderList = new ArrayList<Order>();
	for(int i=0; i< cart.size(); i++) {
		  Order order = new Order();
		order.setUser(cart.get(i).getUser());
		order.setScrapMaterial(cart.get(i).getScrapMaterial());
		order.setOrderDate(java.time.LocalDate.now().toString());
		order.setOrderConfirm("No");
		order.setOrderStatus("Order Placed");
		order.setOrderTotal(cart.get(i).getTotal());
		order.setOrderQuantity(cart.get(i).getQuantity());
		Order k =  orderRepository.save(order);
		
		}	
	}
	
	
	  @CrossOrigin(origins = "*")
	  @PostMapping("/AddContactUsdetails") 
	  public ContactUs addDetails(@RequestBody ContactUs contactUs)throws Exception{
		  System.out.println(contactUs);
		 return contactUsImple.AddContactInfo(contactUs);
	}
	
}
