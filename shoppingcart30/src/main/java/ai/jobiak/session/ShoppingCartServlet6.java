package ai.jobiak.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShoppingCartServlet6
 */
@WebServlet("/cart6")
public class ShoppingCartServlet6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet6() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private ArrayList<Product> getProductIds()
	{
		String userName = "root";
		String password = "Password@123";
		String url = "jdbc:mysql://localhost:3306/world";
			
		Connection con=null;
		ArrayList<Product> productList=new ArrayList<>();
		try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				 con = DriverManager.getConnection(url,userName, password);
					Statement st=con.createStatement();
					String sql="select *from products";
					ResultSet rs=st.executeQuery(sql);
	while(rs.next())
	{
	Product p=new Product(rs.getString(1),rs.getString(2),rs.getDouble(3));
	productList.add(p);
	} 
	}catch (Exception e) 
	{ 
		 e.printStackTrace(); 
		 }
		finally {
			if(con!=null)
			{
				try{
					con.close();
				}catch (Exception e) { 
					 e.printStackTrace(); 
					 }
			}
		}
		return productList;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		double total=0;
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();// .append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		int c=0;
		ArrayList<Product> itemsList=null;
		itemsList=getProductIds();
		out.println("<hr/>");
		for(int i=0;i<itemsList.size();i++){
			out.println("<a href='cart6?item="+itemsList.get(i).getProductId()+"'>Add Items  :"+itemsList.get(i).getProductId()+"</a></br>  ");
			out.println("<a href='cart6?item="+itemsList.get(i).getProductId()+"@delete"+" '>Delete Item  :"+itemsList.get(i).getProductId()+"</a></br>");
			//out.println("<a href='cart6?item="+c+"@delete"+" '>Delete Item  :"+itemsList.get(i).getProductId()+"</a></br>");
		
		}	
		out.print("<hr/>");
	//	System.out.println(ProductId);
	
	     HttpSession shoppingCart=request.getSession(); 
	 	ArrayList<Product> selectedItemsList = null;
	     if(shoppingCart.isNew()) {
	    	 selectedItemsList=new ArrayList<>(); 
		 shoppingCart.setAttribute("items",selectedItemsList );
		 }
	      else {
	    	  //c++;
			 String queryStr=request.getQueryString();
				//System.out.println(queryStr);
				String splitArray[]=queryStr.split("=");
				String ProductId=splitArray[1];
				
				if(ProductId.contains("@delete"))
				{
					
					out.println("Entered delete section");
					String splitArray1[]=ProductId.split("@");
				
					String ProductId1=splitArray1[0];
					
					
						   for(int i=0;i<itemsList.size();i++) {
							   if(itemsList.get(i).getProductId().equals(ProductId1)) {
								
						selectedItemsList=(ArrayList<Product>)shoppingCart.getAttribute("items");
					
						 int pos=(int)selectedItemsList.indexOf(itemsList.get(i));
						 out.print(pos);
						if(selectedItemsList!=null) {
							

					out.println(itemsList.get(i));
					out.println(	selectedItemsList.remove(i));		
						     shoppingCart.setAttribute("items", selectedItemsList);
					break;
							   }
					      }
					
				}
				}
				else {
				
		   for(int i=0;i<itemsList.size();i++) {
			   if(itemsList.get(i).getProductId().equals(ProductId)) {
		selectedItemsList=(ArrayList<Product>)shoppingCart.getAttribute("items");
		selectedItemsList.add(itemsList.get(i));		
		     shoppingCart.setAttribute("items", selectedItemsList);
	break;
			   }
	      }
	      }	
	      }
	     if(selectedItemsList!=null) {
 for(Product p:selectedItemsList){
			out.println(p.getProductId()+"::"+p.getDescription()+"::"+p.getPrice()+"</br>");
			total+=p.getPrice();
 }
 out.println("<hr/>");
		out.println("<h3>Number Items in the cart :"+selectedItemsList.size());
		out.println("<h3> Total : </h3>"+total);
	}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}