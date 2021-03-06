package mcdelivery.controller.handler;

import java.text.DecimalFormat;
import java.util.List;

import mcdelivery.controller.facade.ProductFacade;
import mcdelivery.model.Order;
import mcdelivery.model.OrderedProduct;
import mcdelivery.model.Product;

/**
 * This class implements the business logic to handle the order placed 
 * by the customer.
 * @author Tiang King Jeck
 *
 */
public class OrderHandler {

	private static final double SERVICE_TAX_RATE = 0.06;


	/**
	 * This method computes the total amount of the order
	 * @param amountBeforeTax
	 * @param serviceTax
	 * @return amount after tax
	 */
	private double getAmountAfterTax(double amountBeforeTax, double serviceTax) {

		return amountBeforeTax + serviceTax;

	}


	/**
	 * This method computes tax of a total amount of order
	 * @param amountBeforeTax
	 * @return service tax
	 */
	private double getServiceTax(double amountBeforeTax) {
		
		// Compute service tax
		double serviceTax = amountBeforeTax * SERVICE_TAX_RATE;
		
		// Round off to two decimal places
		serviceTax = Double.valueOf(new DecimalFormat("#.##").format(serviceTax));

		return serviceTax;
		
	}


	/**
	 * This method computes the total amount of placed order
	 * @param order
	 * @return computed order
	 */
	public Order processOrder(Order order) {

		// Initialize total amount before tax
		double amountBeforeTax = 0;
		
		// Get the ordered products list from order
		List<OrderedProduct> orderedProducts = order.getOrderedProducts();
		
		// Loop for compute total amount before tax
		for (OrderedProduct orderedProduct : orderedProducts) {

			// Get product information form database
			ProductFacade productFacade = new ProductFacade();
			Product product = orderedProduct.getProduct();
			product = productFacade.selectProduct(product);
			orderedProduct.setProduct(product);

			// Compute total amount before tax
			amountBeforeTax += orderedProduct.getSubtotal();

		}
		
		// Compute total amount
		double serviceTax = this.getServiceTax(amountBeforeTax);
		double amountAfterTax = this.getAmountAfterTax(amountBeforeTax, serviceTax);

		// Wrap the into order
		order.setOrderedProducts(orderedProducts);
		order.setAmountBeforeTax(amountBeforeTax);
		order.setServiceTax(serviceTax);
		order.setAmountAfterTax(amountAfterTax);

		return order;

	}

}