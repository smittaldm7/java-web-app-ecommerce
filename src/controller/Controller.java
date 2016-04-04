package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.CartItem;
import model.Customer;
import model.Order;
import model.Product;
import model.SupplierProduct;
import service.CartDAO;
import service.CustomerDAO;
import service.OrderDAO;
import service.OrderItemDAO;
import service.ProductKeywordDAO;
import service.SupplierProductDAO;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource ds;
	//private Connection conn;
	
	public void init(ServletConfig config) throws ServletException
    {
		InitialContext initContext = null;
		try 
		{
			initContext = new InitialContext();
			
			Context env = (Context)initContext.lookup("java:comp/env");
			
			ds = (DataSource)env.lookup("jdbc/ecommerce_web");
						
		} 
		catch (NamingException e) 
		{
			throw new ServletException();
		}
    }
		
    public Controller() {
        super();
        

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		//request.removeAttribute("message");
		
		
		
		if(request.getParameter("action").equals("login"))
		{
			if(session.getAttribute("customerID")!=null)
			{
				request.setAttribute("message", "already logged in");
				request.getRequestDispatcher("index.jsp").forward(request,response);
			}
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		
		
		
		else if (request.getParameter("action").equals("logout"))
		{
			if (session.getAttribute("email")==null)
				{
				request.setAttribute("message","Not Logged in");
				}
			else
				{
				session.removeAttribute("email");
				session.removeAttribute("customerID");
				}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
		
		else if(request.getParameter("action").equals("viewCart"))
		{
			if (session.getAttribute("email")==null)
			{
				request.setAttribute("message", "Not logged in");
				request.getRequestDispatcher("index.jsp").forward(request,response);
				
			}
			else
			{
				try {
					CartDAO cartDAO = new CartDAO(ds.getConnection());
					int customerID = (int)(session.getAttribute("customerID"));
					List<CartItem> cartItems = cartDAO.getCartItems(customerID);
					request.setAttribute("cartitems",cartItems);
					request.getRequestDispatcher("viewCart.jsp").forward(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		else if(request.getParameter("action").equals("viewAccount"))
		{
			if (session.getAttribute("email")==null)
			{
				request.setAttribute("message", "Not logged in");
				request.getRequestDispatcher("index.jsp").forward(request,response);
				
			}
			else
			{
				try {
					CustomerDAO customerDAO = new CustomerDAO(ds.getConnection());
					int customerID = (int)(session.getAttribute("customerID"));
					Customer customer = customerDAO.getCustomer(customerID);
					request.setAttribute("customer",customer);
					request.getRequestDispatcher("viewAccount.jsp").forward(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		else if(request.getParameter("action").equals("viewOrders"))
		{
			if (session.getAttribute("email")==null)
			{
				request.setAttribute("message", "Not logged in");
				request.getRequestDispatcher("index.jsp").forward(request,response);
				
			}
			else
			{
				try {
					OrderDAO orderDAO = new OrderDAO(ds.getConnection());
					int customerID = (int)(session.getAttribute("customerID"));
					List<Order> orders = orderDAO.getOrders(customerID);
					for(Order order:orders)
					{
						OrderItemDAO orderItemDAO = new OrderItemDAO(ds.getConnection());
						order.setOrderItems(orderItemDAO.getOrderItems(customerID, order.getId()));
						
					}
					request.setAttribute("orders",orders);
					request.getRequestDispatcher("viewOrders.jsp").forward(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//request.removeAttribute("message");
		
		if(request.getParameter("action").equals("doLogin"))
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User user = new User(email,password);
			
			if(user.validate())
			{
				try {
					//conn=ds.getConnection();
					CustomerDAO customerDAO = new CustomerDAO(ds.getConnection());
					int customerID;
					if( (customerID = customerDAO.getCustomerId(user.getEmail(),user.getPassword())) != 0 )
					{

						session.setAttribute("customerID", customerID);
						session.setAttribute("email", email);
						request.getRequestDispatcher("index.jsp").forward(request,response);
					}
					else
					{
						request.setAttribute("validationmessage", "email or password not recognized" );
						request.getRequestDispatcher("login.jsp").forward(request,response);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				{
					request.setAttribute("validationmessage", user.getMessage() );
					request.getRequestDispatcher("login.jsp").forward(request,response);
				}

		}
		/*
		
		else if(request.getParameter("action").equals("doCreate"))
		{
			User user = new User(request.getParameter("email"),request.getParameter("password"));
			if(user.validate())
			{
				if(request.getParameter("password").equals(request.getParameter("repeatpassword")))
				{
					try {
						
						conn=ds.getConnection();
						
						Account account = new Account(conn);
						if(!account.exists(user.getEmail(),user.getPassword()))
						{
							
							account.create(user.getEmail(),user.getPassword());
							request.setAttribute("email", user.getEmail());
							request.getRequestDispatcher("createsuccess.jsp").forward(request,response);
							
						}
						else
						{
							
							request.setAttribute("validationmessage", "accpunt already exists" );
							request.getRequestDispatcher("createaccount.jsp").forward(request,response);
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
				else
				{
					request.setAttribute("validationmessage", "passwords do not match" );
					request.getRequestDispatcher("createaccount.jsp").forward(request,response);
				}
			}
			else
				{
					request.setAttribute("validationmessage", user.getMessage() );
					request.getRequestDispatcher("createaccount.jsp").forward(request,response);
				}
			

		}*/
		
		else if(request.getParameter("action").equals("doSearch"))
		{
			ProductKeywordDAO productKeywordDAO;
			try 
			{
				productKeywordDAO = new ProductKeywordDAO(ds.getConnection());
				List<Product> products =
						productKeywordDAO.getProductFromSearchString
											(request.getParameter("searchText"));
				
				request.setAttribute("productList",products);
				request.getRequestDispatcher("searchResults.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		else if (request.getParameter("action").equals("viewProduct"))
		{
			try 
			{
				SupplierProductDAO supplierProductDAO = new SupplierProductDAO(ds.getConnection());
				List<SupplierProduct> supplierProductList = 
						supplierProductDAO.getSuppliersForProduct
								(request.getParameter("productName"));
				
				request.setAttribute("supplierProductList",supplierProductList);
				request.getRequestDispatcher("product.jsp").forward(request, response);
			} catch (SQLException e) 
				{
				e.printStackTrace();
				}
			
		}
		
		else if(request.getParameter("action").equals("addToCart"))
		{
			if (session.getAttribute("customerID")==null)
			{
				request.setAttribute("message", "Not logged in");
			}
			else
				{
				int customerID = (int) session.getAttribute("customerID");
				
				String supplierName = request.getParameter("supplierName");
				String productName = request.getParameter("productName");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				try 
				{
				CartDAO cartDAO = new CartDAO(ds.getConnection());
				cartDAO.addCartItem(customerID, productName, supplierName, quantity);
				request.setAttribute("message", "Item added to cart succesfully");
				
				} catch (SQLException e) 
					{
					e.printStackTrace();
					}
				}
				request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
		
		else if(request.getParameter("action").equals("placeOrder"))
		{
			if(session.getAttribute("email")!=null)
			{
				try 
				{
					OrderDAO orderDAO = new OrderDAO(ds.getConnection());
					orderDAO.addOrder((int)(session.getAttribute("customerID")));
					request.setAttribute("message", "Order Placed succesfully");
				} catch (SQLException e) 
					{
					e.printStackTrace();
					}
				
				
			}
			else
			{
				request.setAttribute("message", "Not Logged in");
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
		
		else if(request.getParameter("action").equals("clearCart"))
		{
			if(session.getAttribute("email")!=null)
			{
				try 
				{
					CartDAO cartDAO = new CartDAO(ds.getConnection());
					cartDAO.clearCart((int)(session.getAttribute("customerID")));
				} catch (SQLException e) 
					{
					e.printStackTrace();
					}
				
			}
			else
			{
				request.setAttribute("message", "Not Logged in");
				
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
