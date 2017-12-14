package com.shiptapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.shipt.orderapi.dao.OrdersDAOImplementation;
import com.shipt.orderapi.model.CategoriesPerCustomer;
import com.shipt.orderapi.model.Customer;
import com.shipt.orderapi.model.Products;
import com.shipt.orderapi.model.ProductsPerOrder;

/**
 * @author venkata
 * 
 *         The class {@link OrdersController} returns the list of orders for a given customer of
 *         {@link Customer}.
 *
 */
@Controller
public class OrdersController {

  String dateFormat = "yyyy-MM-dd";
  SimpleDateFormat format = new SimpleDateFormat(dateFormat);

  private final String orderID = "Order ID";
  private final String orderDate = "Order Date";
  private final String orderStatus = "Order Status";
  private final String customerIdentifier = "Customer ID";
  private final String totalAmount = "Total Amount";
  private final String productID = "Product ID";
  private final String productName = "Product Name";
  private final String productPrice = "Product Price";
  private final String productSoldInParts = "Is the Product Sold in Parts";
  private final String productDetails = "productDetails";

  /**
   * @param customerID the unique identifier of {@link Customer}
   * @return the list of orders for a given customer
   * @throws Exception if there is issue with retrieving information from the database.
   */
  @SuppressWarnings("unchecked")
  @ResponseBody
  @RequestMapping(value = "/OrdersAPI/{customerID}", method = RequestMethod.GET)
  public JSONArray getCustomerOrders(@PathVariable("customerID") int customerID) throws Exception {

    JSONArray orderList = new JSONArray();
    OrdersDAOImplementation orderDAOImpl = new OrdersDAOImplementation();
    List<ProductsPerOrder> productsList = orderDAOImpl.ordersByCustomerID(customerID);


    for (int i = 0; i < productsList.size(); i++) {
      String orderDateforProduct = format.format(productsList.get(i).getOrder().getOrderDate());
      JSONObject orderJson = new JSONObject();
      orderJson.put(orderID, productsList.get(i).getOrder().getOrderID());
      orderJson.put(orderDate, orderDateforProduct);
      orderJson.put(orderStatus, productsList.get(i).getOrder().getStatus());
      orderJson.put(customerIdentifier, productsList.get(i).getOrder().getCustomerID());
      orderJson.put(totalAmount, productsList.get(i).getOrder().getTotalAmount());

      List<Products> current_products = productsList.get(i).getProductsPerOrder();

      for (int j = 0; j < current_products.size(); j++) {
        JSONObject product_details = new JSONObject();
        product_details.put(productID, current_products.get(j).getProductID());
        product_details.put(productName, current_products.get(j).getProductName());
        product_details.put(productPrice, current_products.get(j).getProductCost());
        product_details.put(productSoldInParts, current_products.get(j).isProductSoldInParts());
        orderJson.put(productDetails, product_details);
      }
      
      orderList.add(orderJson);
    }

    return orderList;
  }
  
  
  
  /**
   * @param customerID the unique identifier of {@link Customer}
   * @return the list of categories ordered by a given customer
   * @throws Exception if there is issue with retrieving information from the database.
   */
  @SuppressWarnings("unchecked")
  @ResponseBody
  @RequestMapping(value = "/CategoriesAPI/{customerID}", method = RequestMethod.GET)
  public JSONArray getCustomerCategories(@PathVariable("customerID") int customerID) throws Exception {

    JSONArray orderList = new JSONArray();
    OrdersDAOImplementation orderDAOImpl = new OrdersDAOImplementation();
    List<CategoriesPerCustomer> categoryList = orderDAOImpl.categoriesByCustomerID(customerID);


    for (int i = 0; i < categoryList.size(); i++) {
      JSONObject orderJson = new JSONObject();
      orderJson.put("customerID", categoryList.get(i).getCustomerID()); 
      orderJson.put("customerName", categoryList.get(i).getCategoryName());
      orderJson.put("CategoryID", categoryList.get(i).getCategoryID());
      orderJson.put("CategoryName", categoryList.get(i).getCategoryName());
      orderJson.put("Quantity_Ordered", categoryList.get(i).getQunatityOrdered());
      
      orderList.add(orderJson);
    }

    return orderList;
  }
}
