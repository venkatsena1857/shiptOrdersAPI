package com.shipt.orderapi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.shipt.orderapi.model.OrderByDates;

/**
 * @author venkata
 * 
 *         {@link DateRangeDAOImplementation} class interacts with the database and the Controller
 *         to create a list of orders given a date range.
 *
 */
public class DateRangeDAOImplementation implements DateRangeDAO {

  private final String getOrdersBydate =
      "SELECT Order_ID, Customer_Customer_ID, Quantity_Ordered,Products_Product_ID,Order_Date FROM mydb.orders INNER JOIN mydb.order_details ON "
          + "( (mydb.orders.Order_Date BETWEEN ? AND ?) AND mydb.order_details.Orders_Order_ID = mydb.orders.Order_ID)";

  @Override
  public List<OrderByDates> getOrderByDateRange(String start_date, String end_date) {

    List<OrderByDates> orderByDateList = new ArrayList<OrderByDates>();
    if (start_date != null && end_date != null) {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootpass");
        PreparedStatement statement = con.prepareStatement(getOrdersBydate);
        statement.setString(1, start_date);
        statement.setString(2, end_date);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
          orderByDateList.add(new OrderByDates(result.getInt(1), result.getInt(2),
              result.getDouble(3), result.getInt(4), result.getDate(5)));
        }
      } catch (Exception e) {
        System.out.println(e.getMessage().toString());
      }
    }
    return orderByDateList;
  }

}
