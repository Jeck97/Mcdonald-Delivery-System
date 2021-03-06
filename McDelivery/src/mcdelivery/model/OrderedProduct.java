package mcdelivery.model;

import java.text.DecimalFormat;

/**
 * This class represents a product ordered by customer
 * @author Tiang King Jeck
 *
 */
public class OrderedProduct {
	
	private Product product;
	private int quantity;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return Double.valueOf(new DecimalFormat("#.##").
				format(quantity * product.getPrice()));
	}
	
}
