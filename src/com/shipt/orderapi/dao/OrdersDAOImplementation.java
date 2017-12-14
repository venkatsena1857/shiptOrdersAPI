package com.shipt.orderapi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.shipt.orderapi.model.CategoriesPerCustomer;
import com.shipt.orderapi.model.OrderStatus;
import com.shipt.orderapi.model.Orders;
import com.shipt.orderapi.model.Products;
import com.shipt.orderapi.model.ProductsPerOrder;

/**
 * @author venkata
 *
 */
public class OrdersDAOImplementation implements OrdersDAO {

  @Override
  public List<ProductsPerOrder> ordersByCustomerID(int customerID) {
    List<ProductsPerOrder> return_arr = new ArrayList<ProductsPerOrder>();
    if (customerID != 0) {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        System.out.println("worked");
        PreparedStatement statement =
            con.prepareStatement("SELECT * FROM mydb.orders WHERE Customer_Customer_ID=?");
        statement.setInt(1, customerID);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
          List<Products> productsOfOrder = new ArrayList<Products>();
          PreparedStatement nested_statement = con.prepareStatement(
              "SELECT * FROM mydb.products where Product_ID IN (SELECT Products_Product_ID FROM mydb.order_details WHERE Orders_Order_ID=?)");
          nested_statement.setInt(1, result.getInt(1));
          ResultSet nested_result = nested_statement.executeQuery();
          while (nested_result.next()) {
            productsOfOrder.add(new Products(nested_result.getInt(1),nested_result.getString(2),nested_result.getDouble(3),
                nested_result.getBoolean(4), nested_result.getInt(5)));
          }
          Orders currentOrder = new Orders(result.getInt(1), result.getDate(2),
              OrderStatus.valueOf(result.getString(3)), result.getInt(4), result.getInt(5));
          return_arr.add(new ProductsPerOrder(currentOrder,productsOfOrder));
        }
      } catch (Exception e) {
        System.out.println(e.getMessage().toString());
        return null;
      }
    }

    return return_arr;
  }

  public List<CategoriesPerCustomer> categoriesByCustomerID(int customerID) {
    List<CategoriesPerCustomer> return_arr = new ArrayList<CategoriesPerCustomer>();
    if (customerID != 0) {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        System.out.println("worked");
        PreparedStatement statement =
            con.prepareStatement("SELECT Customer_Customer_ID, Customer_Name, Category_ID, Category_Name, Quantity_Ordered FROM "
                + "mydb.orders INNER JOIN mydb.order_details ON mydb.order_details.Orders_Order_ID = mydb.orders.Order_ID "
                + "INNER JOIN mydb.product_categoty ON mydb.order_details.Products_Product_ID = mydb.product_categoty.Products_Product_ID "
                + "INNER JOIN mydb.category ON mydb.category.Category_ID = mydb.product_categoty.Category_Category_ID "
                + "INNER JOIN mydb.customer ON mydb.customer.Customer_ID = mydb.orders.Customer_Customer_ID WHERE mydb.orders.Customer_Customer_ID = ?");
        statement.setInt(1, customerID);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
          CategoriesPerCustomer currentCategories = new CategoriesPerCustomer(result.getInt(1), result.getString(2),
           result.getInt(3), result.getString(4), result.getDouble(5));
          return_arr.add(currentCategories);
        }
      } catch (Exception e) {
        System.out.println(e.getMessage().toString());
        return null;
      }
    }

    return return_arr;
  }

}
