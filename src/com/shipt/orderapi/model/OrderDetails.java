package com.shipt.orderapi.model;

public class OrderDetails {
	private int orderID;
	private int productID;
	private double quantity;
	
	/**
	 * @param orderID unique identifier of the order
	 * @param productID unique identifier of the product
	 * @param quantity the quantity of the products purchased
	 */
	public OrderDetails(int orderID, int productID, double quantity) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.quantity = quantity;
	}
	
	public int getOrderID() {
		return orderID;
	}
	public int getProductID() {
		return productID;
	}
	public double getQuantity() {
		return quantity;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
}
