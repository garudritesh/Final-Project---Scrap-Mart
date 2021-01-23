package com.app.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.ScrapMaterial;
import com.app.entity.User;
import com.app.entity.Cart;
import com.app.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	public List<Order> findByUser(User user);
	
	@Query(value= "SELECT * FROM scrap_order  WHERE  order_confirm = ? ",nativeQuery = true)
	public List<Order> findBystatus(String name);
	
	@Query(value= "SELECT * FROM scrap_order  WHERE  order_confirm = ? and order_status != ? ",nativeQuery = true)
	public List<Order> findBystatus2(String name,String name2);
	
	public Order findByOrderId(int id);
	
	@Transactional
	@Modifying
	@Query(value= "update scrap_order set order_confirm = 'Yes'  WHERE order_id = ? ",nativeQuery = true)
	public void confirmOrder(int id);
	
	@Transactional
	public void deleteByScrapMaterial(ScrapMaterial scrap);

	
}
