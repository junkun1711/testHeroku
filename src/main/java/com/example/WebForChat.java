package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.model.Product;

public class WebForChat {
	
	public String home(Map<String, Object> model, Connection connection) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id, name, description, \"imgPath\", price FROM \"Product\"");

		ArrayList<Product> output = new ArrayList<Product>();
		while (rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setDescription(rs.getString("description"));
			p.setImgPath(rs.getString("imgPath"));
			p.setPrice(rs.getDouble("price"));
			
			output.add(p);
		}

		model.put("records", output);
		
		return "webforchat/home";
	}
	
	public String homeProduct(Map<String, Object> model, Connection connection, HttpServletRequest request) {
		model.put("productId", request.getParameter("productId"));
		
		return "webforchat/productDetail";
	}
	
	public String cart(Map<String, Object> model, Connection connection) {
		
		return "webforchat/cart";
	}
	
	public String order(Map<String, Object> model, Connection connection) {
		
		return "webforchat/order";
	}
	
	public String user(Map<String, Object> model, Connection connection) {
		
		return "webforchat/user";
	}
	
	public String manager(Map<String, Object> model, Connection connection) {
		
		return "webforchat/manager";
	}
	
}
