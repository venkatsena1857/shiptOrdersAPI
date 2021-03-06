package com.shipt.orderapi.model;

/**
 * @author venka
 *
 */
public class CategoriesPerCustomer {
    private int customerID;
    private String customerName;
    private int categoryID;
    private String categoryName;
    private double qunatityOrdered;
    
    /**
     * @param customerID the unique identification of each customer
     * @param customerName name  of each customer
     * @param categoryID unique identifier for category of product purchased
     * @param categoryName name of category of product 
     * @param qunatityOrdered the quantity of product ordered by customer
     */
    public CategoriesPerCustomer(int customerID, String customerName, int categoryID,
        String categoryName, double qunatityOrdered) {
      super();
      this.customerID = customerID;
      this.customerName = customerName;
      this.categoryID = categoryID;
      this.categoryName = categoryName;
      this.qunatityOrdered = qunatityOrdered;
    }
    public int getCustomerID() {
      return customerID;
    }
    public String getCustomerName() {
      return customerName;
    }
    public int getCategoryID() {
      return categoryID;
    }
    public String getCategoryName() {
      return categoryName;
    }
    public double getQunatityOrdered() {
      return qunatityOrdered;
    }
    public void setCustomerID(int customerID) {
      this.customerID = customerID;
    }
    public void setCustomerName(String customerName) {
      this.customerName = customerName;
    }
    public void setCategoryID(int categoryID) {
      this.categoryID = categoryID;
    }
    public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
    }
    public void setQunatityOrdered(double qunatityOrdered) {
      this.qunatityOrdered = qunatityOrdered;
    }
}
