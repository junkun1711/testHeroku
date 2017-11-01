/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication
public class Main {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

	@RequestMapping("/")
	String index() {
		return "index";
	}

	@RequestMapping("/db")
	String db(Map<String, Object> model) {
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
			stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
			ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getTimestamp("tick"));
			}

			model.put("records", output);
			return "db";
		} catch (Exception e) {
			model.put("message", e.getMessage());
			return "error";
		}
	}

	@Bean
	public DataSource dataSource() throws SQLException {
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	}

	/** Below is the content of web for chat  */
	@Autowired
	private WebForChat webForChat;

	@RequestMapping("/wfc/{rootPath}")
	String wfcRootRoute(@PathVariable String rootPath, Map<String, Object> model) {
		try {
			Method m = WebForChat.class.getDeclaredMethod(rootPath, Map.class);
			model.put("active_" + rootPath, "active");
			return (String) m.invoke(webForChat, model);
		} catch (Exception e) {
			model.put("message", e.getMessage());
			return "webforchat/error";
		}
	}
	
	@RequestMapping("/wfc/{rootPath}/{subPath}")
	String wfcSubRoute(@PathVariable String rootPath, @PathVariable String subPath, Map<String, Object> model, HttpServletRequest request) {
		try {
			String methodName = rootPath + subPath.toUpperCase().substring(0, 1) + subPath.substring(1);
			Method m = WebForChat.class.getDeclaredMethod(methodName, Map.class, HttpServletRequest.class);
			model.put("active_" + rootPath, "active");
			return (String) m.invoke(webForChat, model, request);
		} catch (Exception e) {
			model.put("message", e.getMessage());
			return "webforchat/error";
		}
	}

	@Bean
	public WebForChat webForChat() {
		return new WebForChat();
	}
}
