package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.ContactUs;
import com.app.repo.ContactRepositry;

@Service
public class ContactUsImple {

	@Autowired
	private ContactRepositry contactRepositry;
	
	public  ContactUs AddContactInfo(ContactUs contactUs) {
		 return contactRepositry.save(contactUs);
	}
}
