package com.app.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.ScrapMaterial;
import com.app.entity.User;
import com.app.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	public List<Cart> findByUser(User user);
	
	public List<Cart> findByUserAndScrapMaterial(User user, ScrapMaterial scrap);
	
	@Transactional
	public void deleteByScrapMaterial(ScrapMaterial scrap);
	
}
