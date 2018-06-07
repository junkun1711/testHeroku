package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Product;

@Component
public class WebForChat {
	@Autowired
	private DataSource dataSource;
	
	public String home(Map<String, Object> model) throws SQLException {
		Connection connection = dataSource.getConnection();
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
	
	public String homeProduct(Map<String, Object> model, HttpServletRequest request) {
		model.put("productId", request.getParameter("productId"));
		
		return "webforchat/productDetail";
	}
	
	public String cart(Map<String, Object> model) {
		
		return "webforchat/cart";
	}
	
	public String order(Map<String, Object> model) {
		
		return "webforchat/order";
	}
	
	public String user(Map<String, Object> model) {
		
		return "webforchat/user";
	}
	
	public String manager(Map<String, Object> model) {
		
		return "webforchat/manager";
	}
	
}
