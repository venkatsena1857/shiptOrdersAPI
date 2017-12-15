package com.shipt.orderapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author venkata
 *
 */
public class ProductsPerOrder {
  private Orders order;
  private List<Products> productsPerOrder = new ArrayList<Products>();
  
  /**
   * @param order {@link Orders} class object used to access orders
   * @param productsPerOrder {@link ProductsPerOrder} object used to store products with their 
   *        respective orders
   */
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
