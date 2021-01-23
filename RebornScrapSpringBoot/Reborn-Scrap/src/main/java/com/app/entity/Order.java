package com.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="scrap_order")
public class Order {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name="scrap_id")
	private ScrapMaterial scrapMaterial;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name="address_id")
//	private Address address;
	
	private int orderQuantity;
	private String orderDate;
	private String shippingDate;
	private String orderStatus;
	private String orderConfirm;
	private int orderTotal;
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ScrapMaterial getScrapMaterial() {
		return scrapMaterial;
	}
	public void setScrapMaterial(ScrapMaterial scrapMaterial) {
		this.scrapMaterial = scrapMaterial;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderConfirm() {
		return orderConfirm;
	}
	public void setOrderConfirm(String orderConfirm) {
		this.orderConfirm = orderConfirm;
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", user=" + user + ", scrapMaterial=" + scrapMaterial + ", orderQuantity="
				+ orderQuantity + ", orderDate=" + orderDate + ", shippingDate=" + shippingDate + ", orderStatus="
				+ orderStatus + ", orderConfirm=" + orderConfirm + ", orderTotal=" + orderTotal + "]";
	}
	
	
}
