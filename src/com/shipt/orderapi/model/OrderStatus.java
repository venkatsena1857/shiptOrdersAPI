package com.shipt.orderapi.model;

/**
 * @author venkata
 *
 */
public enum OrderStatus {
	/**
	 * Indicates that the order is waiting for delivery
	 */
	WAITING_FOR_DELIVERY, /**
	 * Indicates that the order is delivered
	 */
	DELIVERED, /**
	 * Indicates that the order is on its way
	 */
	ON_ITS_WAY;
}
