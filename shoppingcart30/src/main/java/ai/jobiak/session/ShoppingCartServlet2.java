package ai.jobiak.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Product
 */
@WebServlet("/product")
public class ShoppingCartServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
	    response.setContentType("text/html");
	    for(int i=1;i<=10;i++)
	    {
	    	out.println("<a href = 'product'> Add Item"+i+"</a><br/>");
	    }
	    
	    
	    
	  //  out.println("<a href = 'product'> Add Item</a>");
	    
	    String queryStr = request.getQueryString();
	    System.out.println(queryStr);
	    String splitArray[]=queryStr.split("=");
	    String productid = splitArray[1];
	    
	    
	    
	    
	    
	    ArrayList<Product> itemList = null;
	   HttpSession shoppingCart = request.getSession();
	   
	   if(shoppingCart.isNew())
	   {
		   itemList = new ArrayList<>();
		   shoppingCart.setAttribute("items", itemList);
	   }
	   else
	   {
		   itemList = (ArrayList<Product>)shoppingCart.getAttribute("items");
		   Product p = new Product (productid,"ABC",456.98);
		   
		   itemList.add(p);
		   
		   for(Product product :itemList)
		   {
			   out.println(product.getDescription()+"::"+product.getProductId()+"::"+product.getPrice());;
		   }
		   
		  
		   
		   
	   }
	
	   out.println("<h3>Items in the Cart#"+itemList.size());
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
