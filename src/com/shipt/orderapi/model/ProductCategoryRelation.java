package com.shipt.orderapi.model;

/**
 * @author venkata
 *
 */
public class ProductCategoryRelation {
	private int productID;
	private int categoryID;

	/**
	 * @param productID unique identifier of product
	 * @param categoryID unique identifier of category of product
	 */
	public ProductCategoryRelation(int productID, int categoryID) {
		super();
		this.productID = productID;
		this.categoryID = categoryID;
	}
	
	public int getProductID() {
		return productID;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	
}
