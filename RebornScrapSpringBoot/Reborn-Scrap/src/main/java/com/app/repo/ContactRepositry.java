package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.ContactUs;

@Repository
public interface ContactRepositry extends JpaRepository<ContactUs, Integer> {
	
	//public ContactUs findbyContactUsId(ContactUs contactUs);
}
