package mcdelivery.model;

import java.util.List;

/**
 * This class represents an order made by customer
 * @author Tiang King Jeck
 *
 */
public class Order {
	
	private int id;
	private Customer customer;
	private double amountBeforeTax;
	private double serviceTax;
	private double amountAfterTax;
	private int paymentMethod;
	private List<OrderedProduct> orderedProducts;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getAmountBeforeTax() {
		return amountBeforeTax;
	}
	public void setAmountBeforeTax(double amountBeforeTax) {
		this.amountBeforeTax = amountBeforeTax;
	}
	public double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(double serviceTax) {
		this.serviceTax = serviceTax;
	}
	public double getAmountAfterTax() {
		return amountAfterTax;
	}
	public void setAmountAfterTax(double amountAfterTax) {
		this.amountAfterTax = amountAfterTax;
	}
	public int getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public List<OrderedProduct> getOrderedProducts() {
		return orderedProducts;
	}
	public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

}
