package com.shiptapi.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shipt.orderapi.dao.DateRangeDAOImplementation;
import com.shipt.orderapi.model.OrderByDates;

/**
 * @author venkata
 * 
 *         DateAPIController is the controller class to accepts a date range and a day, week, or
 *         month and returns a breakdown of products sold by quantity per day/week/month.
 * 
 */
@Controller
@RequestMapping("/dateAPI")
public class DateAPIController {
  private final String orderID = "OrderID";
  private final String customerID = "CustomerID";
  private final String quantitesOrdered = "Quantities Ordered";
  private final String productID = "Product ID";
  private final String date = "Date";
  private final String datePattern = "yyyy-MM-dd";

  private enum frequency {
    WEEKLY, MONTHLY, DAILY
  };

  private final int incrementByOne = 1;

  SimpleDateFormat format = new SimpleDateFormat(datePattern);


  /**
   * @param startDate Starting date in the date range.
   * @param endDate Last date in the date range.
   * @param orderFrequency is the {@link Enum} to check order frequencies. Takes values {WEEKLY,
   *        MONTHLY, DAILY}.
   * @return JSONObject which is the breakdown of products
   * @throws Exception for checked exceptions.
   */
  @SuppressWarnings("unchecked")
  @ResponseBody
  @RequestMapping(value = "/{startDate}/{endDate}/{orderFrequency}", method = RequestMethod.GET)
  public JSONObject dateRangeDailyAPIMethod(@PathVariable("startDate") String startDate,
      @PathVariable("endDate") String endDate,
      @PathVariable("orderFrequency") String orderFrequency) throws Exception {
    JSONObject jsonOutput = new JSONObject();
    if (!orderFrequency.equalsIgnoreCase(frequency.WEEKLY.toString())
        && !orderFrequency.equalsIgnoreCase(frequency.DAILY.toString())
        && !orderFrequency.equalsIgnoreCase(frequency.MONTHLY.toString())) {
      jsonOutput.put("Error", "Incorrect URL");
      return jsonOutput;
    }
    FileWriter writeFile = null;
    if (orderFrequency.equalsIgnoreCase(frequency.WEEKLY.toString())) {
      try {
        writeFile = new FileWriter("E:\\ordersByWeekly.csv");
        writeFile.write(orderID + "," + customerID + ", " + quantitesOrdered + ", " + productID
            + ", " + "weekOf");
      } catch (Exception e) {
        System.out.println(e.getMessage().toString());
      }
    }
    if (orderFrequency.equalsIgnoreCase(frequency.MONTHLY.toString())) {
      try {
        writeFile = new FileWriter("E:\\ordersByMonthly.csv");
        writeFile.write(orderID + ", " + customerID + ", " + quantitesOrdered + ", " + productID
            + ", " + "monthOf");

      } catch (Exception e) {
        System.out.println(e.getMessage().toString());
      }
    }
    if (orderFrequency.equalsIgnoreCase(frequency.DAILY.toString())) {
      try {
        writeFile = new FileWriter("E:\\ordersByDaily.csv");
        writeFile.write(
            orderID + "," + customerID + ", " + quantitesOrdered + ", " + productID + ", " + date);
      } catch (Exception e) {
        System.out.println(e.getMessage().toString());
      }
    }

    Map<String, List<OrderByDates>> productsByFrequency =
        getProductsByFrequency(orderFrequency, startDate, endDate);

    Iterator iterator = productsByFrequency.entrySet().iterator();
    while (iterator.hasNext()) {
      JSONArray eachFrequencyJson = new JSONArray();
      Map.Entry frequencyOrderPair = (Map.Entry) iterator.next();
      String breakdownKey = null;
      ArrayList<OrderByDates> ordersList = (ArrayList<OrderByDates>) frequencyOrderPair.getValue();
      for (int i = 0; i < ordersList.size(); i++) {
        String orderDate = format.format(ordersList.get(i).getDate());
        JSONObject eachOrderJson = new JSONObject();
        eachOrderJson.put(orderID, ordersList.get(i).getOrderID());
        eachOrderJson.put(customerID, ordersList.get(i).getCustomerID());
        eachOrderJson.put(quantitesOrdered, ordersList.get(i).getNumber_of_items());
        eachOrderJson.put(productID, ordersList.get(i).getProduct_ID());
        eachOrderJson.put(date, orderDate);
        if (orderFrequency.equalsIgnoreCase(frequency.WEEKLY.toString())) {
          breakdownKey = "break down for week : " + frequencyOrderPair.getKey().toString();
        }
        if (orderFrequency.equalsIgnoreCase(frequency.MONTHLY.toString())) {
          breakdownKey =
              "break down for month: " + frequencyOrderPair.getKey().toString().substring(0, 7);
        }
        if (orderFrequency.equalsIgnoreCase(frequency.DAILY.toString())) {
          breakdownKey = "break down for day: " + frequencyOrderPair.getKey().toString();
        }
        eachFrequencyJson.add(eachOrderJson);
        writeFile.write(System.getProperty("line.separator"));
        String writeTOCsV =
            eachOrderJson.get(orderID).toString() + "," + eachOrderJson.get(customerID).toString()
                + "," + eachOrderJson.get(quantitesOrdered).toString() + ","
                + eachOrderJson.get(productID).toString() + ","
                + frequencyOrderPair.getKey().toString();
        writeFile.append(writeTOCsV);
      }
      if (ordersList.size() > 0) {
        jsonOutput.put(breakdownKey, eachFrequencyJson);
      }
    }
    writeFile.flush();
    writeFile.close();
    return jsonOutput;
  }

  /**
   * Helper function to get products list by frequency by options given a date range.
   * 
   * @param option the frequency at which the products are listed.
   * @param startingDate the starting date in the range.
   * @param endingDate the ending date in the range.
   * @return a {@link Map}<String,List<OrderByDates> which is a pair of Dates and the orders for
   *         that particular date.
   * 
   * @exception ParseException when unable to parse dates.
   */
  private Map<String, List<OrderByDates>> getProductsByFrequency(String option, String startingDate,
      String endingDate) throws ParseException {
    DateRangeDAOImplementation dateRangeObject = new DateRangeDAOImplementation();
    List<OrderByDates> ordersList = dateRangeObject.getOrderByDateRange(startingDate, endingDate);
    Map<String, List<OrderByDates>> frequencyOrderMap = new HashMap<String, List<OrderByDates>>();

    Date startDateRange = format.parse(startingDate);
    Date endDateRange = format.parse(endingDate);
    LocalDate startDate = startDateRange.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate endDate = endDateRange.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate nextEndDate = null;
    if (option.equalsIgnoreCase(frequency.WEEKLY.toString())) {
      endDate = startDate.plusWeeks(incrementByOne);
    }
    if (option.equalsIgnoreCase(frequency.MONTHLY.toString())) {
      endDate = startDate.plusMonths(incrementByOne);
    }
    if (option.equalsIgnoreCase(frequency.DAILY.toString())) {
      endDate = startDate.plusDays(incrementByOne);
    }

    for (int i = 0; i < ordersList.size();) {
      if (option.equalsIgnoreCase(frequency.WEEKLY.toString())) {
        nextEndDate = endDate.plusWeeks(incrementByOne);
      }
      if (option.equalsIgnoreCase(frequency.MONTHLY.toString())) {
        nextEndDate = endDate.plusMonths(incrementByOne);
      }
      if (option.equalsIgnoreCase(frequency.DAILY.toString())) {
        endDate = startDate.plusDays(incrementByOne);
      }
      List<OrderByDates> productsByFrequency = new ArrayList<OrderByDates>();
      String keyDate = startDate.toString();
      while (startDate.isBefore(endDate) && i < ordersList.size()) {
        String cur_date = format.format(ordersList.get(i).getDate());
        Date currentDate = format.parse(cur_date);
        LocalDate current_date =
            currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (current_date.isBefore(endDate)) {

          productsByFrequency.add(ordersList.get(i));
          i++;
        }
        startDate = current_date;
      }
      frequencyOrderMap.put(keyDate, productsByFrequency);
      startDate = endDate;
      endDate = nextEndDate;
    }
    return frequencyOrderMap;
  }
}
