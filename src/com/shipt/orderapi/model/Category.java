package com.shipt.orderapi.model;

public class Category {
	private int categoryID;
	private String categoryName;
	
	public Category(int categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
