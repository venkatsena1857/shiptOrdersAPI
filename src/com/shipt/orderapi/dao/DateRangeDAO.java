package com.shipt.orderapi.dao;

import java.util.List;
import com.shipt.orderapi.model.OrderByDates;

/**
 * @author venkata 
 * 
 * {@link DateRangeDAO} interacts with the DAO and the controller.
 *
 */
public interface DateRangeDAO {
  
  /** 
   * The method interacts with the database to retrieve the orders made in the date range duration.
   * 
   * @param start_date the starting date of the date range
   * @param end_date the ending date of the date range
   * @return the list of orders by dates.
   */
  public List<OrderByDates> getOrderByDateRange(String start_date, String end_date);
}
