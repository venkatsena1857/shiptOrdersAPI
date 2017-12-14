package com.shipt.orderapi.model;

import java.util.Date;

/**
 * @author venkata
 *
 */
public class OrderByDates {
  private int orderID;
  private int customerID;
  private double number_of_items;
  private int product_ID;
  private Date date;
  
  /**
   * Constructor for the class {@link OrderByDates}. 
   * 
   * @param orderID the unique identifier of {@link Orders}
   * @param customerID the unique identifier for {@link Customer}
   * @param productQuantity product quantity purchased by the customer
   * @param productID the unique identifier for {@link Products} 
   * @param date the {@link Date} on which the order was made.
   */
  public OrderByDates(int orderID, int customerID, double productQuantity, int productID, Date date) {
    super();
    this.orderID = orderID;
    this.customerID = customerID;
    this.number_of_items = productQuantity;
    this.product_ID = productID;
    this.date = date;
    
  }

  public int getOrderID() {
    return orderID;
  }

  public int getCustomerID() {
    return customerID;
  }

  public double getNumber_of_items() {
    return number_of_items;
  }

  public void setOrderID(int orderID) {
    this.orderID = orderID;
  }

  public void setCustomerID(int customerID) {
    this.customerID = customerID;
  }

  public void setNumber_of_items(double number_of_items) {
    this.number_of_items = number_of_items;
  }

  public int getProduct_ID() {
    return product_ID;
  }

  public void setProduct_ID(int product_ID) {
    this.product_ID = product_ID;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }  
}
