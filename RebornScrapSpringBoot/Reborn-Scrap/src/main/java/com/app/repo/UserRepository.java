package com.app.repo;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUserMail(String tempMail);
	
	public User findByUserMailAndUserPassword(String tempPassword,String passTemp);
	
	public User findByUserId(int id);
	
	@Query(value= "SELECT * FROM user WHERE user_type = ?",nativeQuery = true)
	List<User> findAllUsers(String users);
	
	@Transactional
	@Modifying
		@Query(value= "DELETE FROM user WHERE user_id = ?",nativeQuery = true)
		public int removeByUserId(int userId);
}

