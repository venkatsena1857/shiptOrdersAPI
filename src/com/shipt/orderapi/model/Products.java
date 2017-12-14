package com.shipt.orderapi.model;

public class Products {
	private int productID;
	private String productName;
	private double productCost;
	private boolean productSoldInParts;
	private double quantityInStock;
	
	
	public Products(int productID, String productName, double productCost, boolean productSoldInParts,
			double quantityInStock) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productCost = productCost;
		this.productSoldInParts = productSoldInParts;
		this.quantityInStock = quantityInStock;
	}
	public int getProductID() {
		return productID;
	}
	public String getProductName() {
		return productName;
	}
	public double getProductCost() {
		return productCost;
	}
	public boolean isProductSoldInParts() {
		return productSoldInParts;
	}
	public double getQuantityInStock() {
		return quantityInStock;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}
	public void setProductSoldInParts(boolean productSoldInParts) {
		this.productSoldInParts = productSoldInParts;
	}
	public void setQuantityInStock(double quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	
	
}
