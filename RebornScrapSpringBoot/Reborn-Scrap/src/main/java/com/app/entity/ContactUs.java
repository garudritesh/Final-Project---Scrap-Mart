package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact_us")
public class ContactUs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="contact_id")
	private int contactId;
	
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="user_mail")
	private String userMail;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="message")
	private String message;

	public ContactUs() {
		
	}

	public ContactUs(int contactId, String firstName, String lastName, String userMail, String message) {
		super();
		this.contactId = contactId;
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.userMail = userMail;
		this.subject=subject;
		this.message = message;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ContactUs [contactId=" + contactId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userMail=" + userMail + " ,subject= "+ subject +" ,message=" + message + "]";
	}

}

