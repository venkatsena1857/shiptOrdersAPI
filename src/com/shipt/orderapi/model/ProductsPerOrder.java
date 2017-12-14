package com.shipt.orderapi.model;

import java.util.ArrayList;
import java.util.List;

public class ProductsPerOrder {
  private Orders order;
  private List<Products> productsPerOrder = new ArrayList<Products>();
  
  public ProductsPerOrder(Orders order, List<Products> productsPerOrder) {
    super();
    this.order = order;
    this.productsPerOrder = productsPerOrder;
  }
  
  public Orders getOrder() {
    return order;
  }
  public List<Products> getProductsPerOrder() {
    return productsPerOrder;
  }
  public void setOrder(Orders order) {
    this.order = order;
  }
  public void setProductsPerOrder(List<Products> productsPerOrder) {
    this.productsPerOrder = productsPerOrder;
  }
}
