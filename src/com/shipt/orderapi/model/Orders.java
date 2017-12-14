package com.shipt.orderapi.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author venkata
 * 
 * {@link Orders} class is a model for the order details. 
 *
 */
@JsonPropertyOrder({"orderID", "orderDate", "customerID","totalAmount","status"})
public class Orders {
	private int orderID;
	private Date orderDate;
	private int customerID;
	private int totalAmount;
	private OrderStatus status;
	
	/**
	 * The constructor for {@link Orders}
	 * @param orderID the unique identifier for {@link Orders}. 
	 * @param orderDate the date on which the order was placed
	 * @param status the status of the order. This {@link OrderStatus} can have values {ON_THE_WAY, DELIVERED, SHIPPED}
	 * @param customerID the unique identifier for {@link Customer}
	 * @param totalAmount the price of the order.
	 */
	public Orders(int orderID, Date orderDate, OrderStatus status, int customerID, int totalAmount) {
		super();
		if(orderID > 0 && orderDate != null && customerID > 0 && 
				totalAmount > -1 && status != null){
			this.orderID = orderID;
			this.orderDate = orderDate;
			this.customerID = customerID;
			this.totalAmount = totalAmount;
			this.status = status;
		}
	}
	
	public int getOrderID() {
		return orderID;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public int getCustomerID() {
		return customerID;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
}
