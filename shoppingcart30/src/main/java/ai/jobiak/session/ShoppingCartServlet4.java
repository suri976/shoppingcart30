package ai.jobiak.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/cart3")
public class ShoppingCartServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws SQLException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
  private String[] getProductIds() throws SQLException {  
    String url="jdbc:mysql://localhost:3306/world";
	String uname="root";
	String pass="Password@123";
	String productIds[]=new String[10];
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	Connection	con = DriverManager.getConnection(  
				url,uname,pass);

	String selectSQL="select productId from products";
	
	Statement	st = con.createStatement();
		ResultSet rs1 = st.executeQuery(selectSQL);
		int i=0;
		while(rs1.next())
		{
			productIds[i]=rs1.getString(1);
			i++;
		}
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	

	
	return productIds;

    
  }
    
  private String[] getProductDescription() throws SQLException {  
	    String url="jdbc:mysql://localhost:3306/world";
		String uname="root";
		String pass="Password@123";
		String productdescription[]=new String[10];
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		Connection	con = DriverManager.getConnection(  
					url,uname,pass);

		String selectSQL="select description from products";
		
		Statement	st = con.createStatement();
			ResultSet rs1 = st.executeQuery(selectSQL);
			int i=0;
			while(rs1.next())
			{
			//	productdescription[i]=rs1.getString(2);
				i++;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
		return productdescription;

	    
	  }
	    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		String productdescriptions[]= null;
		
		try {
			productdescriptions= getProductDescription();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String productIds[] = null;
		try {
			productIds = getProductIds();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//int n;
		response.setContentType("text/html");
		for(int i=0;i<productIds.length;i++) {
		out.println("<a href = 'cart3?item="+productIds[i]+"'>Add Items " +productIds[i]+"</a><br>");
		
		
		}
		String quertString = request.getQueryString();
		//out.println(quertString);
		String splitArray[]=quertString.split("=");
		String productId=splitArray[1];
		
		
		ArrayList<Product>itemList = null;
		
		HttpSession shoppingCart = request.getSession();
		
		if(shoppingCart.isNew()) {
		itemList = new ArrayList<>();
		
		shoppingCart.setAttribute("items", itemList);
		
		
		}
		else {
			
			itemList = (ArrayList<Product>)shoppingCart.getAttribute("items");
			itemList.add( new Product(productId, "Bike",123000));
			for(int i=0;i<10;i++)
			{
				
			}
			//itemList.add(p);
			shoppingCart.setAttribute("items", itemList);
			
			
		}
		double totalcost=0;
		
		for(Product product : itemList) {
			
			out.println(" "+product.getProductId()+"::"+product.getDescription()+"::"+product.getPrice()+"<br>");
		}
		out.println("<h3> No of items in cart </h3>"+itemList.size());
		
		out.println(totalcost);
		}

}