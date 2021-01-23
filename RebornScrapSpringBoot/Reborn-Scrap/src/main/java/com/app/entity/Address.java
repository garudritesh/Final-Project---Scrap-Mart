//package com.app.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="address")
//public class Address {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="address_id")
//	private int addressId;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name="user_id")
//	private User user;
//	
//	private String addressName;
//	private String addressStreet1;
//	private String addressStreet2;
//	private String addressCity;
//	private String addressState;
//	private String addressCountry;
//	private String addressZipcode;
//}
