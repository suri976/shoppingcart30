//package ai.jobiak.session;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//
///**
// * Servlet implementation class ShoppingCartServlet
// */
//@WebServlet("/cart5")
//public class Books extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Books() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @throws SQLException 
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//    
//  private String[] getProductIds() throws SQLException {  
//    String url="jdbc:mysql://localhost:3306/world";
//	String uname="root";
//	String pass="Password@123";
//	String productIds[]=new String[7];
//	try {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//	Connection	con = DriverManager.getConnection(  
//				url,uname,pass);
//
//	String selectSQL="select id from book";
//	
//	Statement	st = con.createStatement();
//		ResultSet rs1 = st.executeQuery(selectSQL);
//		int i=0;
//		while(rs1.next())
//		{
//			productIds[i]=rs1.getString(1);
//			i++;
//		}
//	} catch (Exception e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	return productIds;
//    
//  }
//  private String[] getProductDes() throws SQLException {  
//	    String url="jdbc:mysql://localhost:3306/world";
//		String uname="root";
//		String pass=Pass.getPassw();
//		String productDes[]=new String[7];
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection	con = DriverManager.getConnection(  
//					url,uname,pass);
//
//		String selectSQL="select desc from book";
//		
//		Statement	st = con.createStatement();
//			ResultSet rs1 = st.executeQuery(selectSQL);
//			int i=0;
//			while(rs1.next())
//			{
//				productDes[i]=rs1.getString(1);
//				i++;
//			}
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return productDes;
//	    
//	  }
//  private String[] getProductPrice() throws SQLException {  
//	    String url="jdbc:mysql://localhost:3306/world";
//		String uname="root";
//		String pass=Pass.getPassw();
//		String productPri[]=new String[7];
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection	con = DriverManager.getConnection(  
//					url,uname,pass);
//
//		String selectSQL="select price from book";
//		
//		Statement	st = con.createStatement();
//			ResultSet rs1 = st.executeQuery(selectSQL);
//			int i=0;
//			while(rs1.next())
//			{
//				productPri[i]=rs1.getString(1);
//				i++;
//			}
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return productPri;
//	    
//	  }
//  
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		PrintWriter out = response.getWriter();
//		String productIds[] = null;
//		String productDes[] = null;
//		String productPri[]=null;
//		double total=0;
//		try {
//			productDes = getProductDes();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			productIds = getProductIds();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			productPri=getProductPrice();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//int n;
//		response.setContentType("text/html");
//		for(int i=0;i<productIds.length-1;i++) {
//		out.println("<a href = 'cart5?item="+productIds[i]+"'>Add Items " +productIds[i]+"</a><br>");
//		
//		
//		}
//		String quertString = request.getQueryString();
//		//out.println(quertString);
//		String splitArray[]=quertString.split("=");
//		String productId=splitArray[1];
//		
//		//String splitArray1[]=(String) splitArray[1];
//		//String productdes=splitArray[0];
////		String productprice=splitArray[2];
////		double price=Double.parseDouble(productprice);
//		ArrayList<Product>itemList = null;
//		
//		HttpSession shoppingCart = request.getSession();
//		//int p=Integer.parseInt(productId);
//		//String desc=productDes[p];
//		  Map<String, String> query_pairs = new HashMap<String, String>();
//		  int n=productIds.length;
//		  
//		  for(int y=0;y<n;y++)
//		  query_pairs.put(productIds[y], productPri[y]);
//		  
//		if(shoppingCart.isNew()) {
//		itemList = new ArrayList<>();
//		
//		shoppingCart.setAttribute("items", itemList);
//		
//		
//		}
//		else {
//			double price=Double.parseDouble(query_pairs.get(productId));
//			System.out.println(price);
//			itemList = (ArrayList<Product>)shoppingCart.getAttribute("items");
//			itemList.add( new Product(productId,"Books" ,price));
//			//itemList.add(p);
//			shoppingCart.setAttribute("items", itemList);
//			
//			
//		}
//		for(Product product : itemList) {
//			out.println(" "+product.getProductId()+"::"+product.getPrice()+"<br>");
//			total+=product.getPrice();
//		}
//		out.println("<h3> No of items in cart </h3>"+itemList.size());
//		out.println("<h3> Total : </h3>"+total);
//		}
//
//}