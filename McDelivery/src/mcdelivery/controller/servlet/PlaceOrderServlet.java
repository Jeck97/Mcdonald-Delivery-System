package mcdelivery.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mcdelivery.model.Order;
import mcdelivery.model.OrderedProduct;
import mcdelivery.model.Product;

/**
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/placeOrder/placeOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlaceOrderServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get session attribute and cast to order object
		HttpSession session = request.getSession();
		Order order  = (Order) session.getAttribute("order");

		// Check whether the order is null
		if (order == null) {
			
			// Initialize the order and the list if the session is null
			order = new Order();
			order.setOrderedProducts(new ArrayList<OrderedProduct>());

		}

		// Get data from web form
		int productId = Integer.parseInt(request.getParameter("product"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		// Warp data into object of OrderedProduct
		Product product = new Product();
		product.setId(productId);

		OrderedProduct orderedProduct = new OrderedProduct();
		orderedProduct.setProduct(product);
		orderedProduct.setQuantity(quantity);

		// Add object of OrderedProduct into list
		order.getOrderedProducts().add(orderedProduct);


		// Add order to session
		session.setAttribute("order", order);
		session.setAttribute("numberOfOrder", order.getOrderedProducts().size());

		// Redirect to the same page
		response.sendRedirect("orderForm.jsp");

	}

}
