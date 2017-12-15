package com.shipt.orderapi.dao;
import java.util.List;
import com.shipt.orderapi.model.CategoriesPerCustomer;
import com.shipt.orderapi.model.Customer;
import com.shipt.orderapi.model.Orders;
import com.shipt.orderapi.model.ProductsPerOrder;
import com.shiptapi.controller.OrdersController;

/**
 * @author venkata
 * 
 * {@link OrdersDAO} interacts with the database and {@link OrdersController}.
 *
 */
public interface OrdersDAO {
	/**
	 * The method retrieves the orders by a customer given the {@code customerID}
	 * @param CustomerID the unique identifier of {@link Customer}
	 * @return {@link List} of products in the order.
	 */
	public List<ProductsPerOrder> ordersByCustomerID(int CustomerID);
	
	/**
	 * The method retrieves the categories in orders by a customer given the {@code customerID}
	 * @param customerID the unique identifier of {@link Customer}
	 * @return{@link List} of categories in the order.
	 */
	public List<CategoriesPerCustomer> categoriesByCustomerID(int customerID);
	
}
