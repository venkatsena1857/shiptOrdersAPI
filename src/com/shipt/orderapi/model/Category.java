package com.shipt.orderapi.model;

/**
 * @author venkata
 *
 */
public class Category {
	private int categoryID;
	private String categoryName;
	
	/**
	 * @param categoryID unique identifier for category of product 
	 * @param categoryName name of category of product 
	 */
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
