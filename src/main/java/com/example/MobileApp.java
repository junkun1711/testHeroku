package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mb")
public class MobileApp {

	@Autowired
	private DataSource dataSource;
	
	@RequestMapping("")
	String index() {
		return "index";
	}
	
	@RequestMapping("/db")
	String db(@RequestParam String abc) {
		
		return abc;
	}
	
	@RequestMapping("/db2")
	String db2(@RequestBody Map<String,Object> abc) {
		
		return abc.toString();
	}
}
