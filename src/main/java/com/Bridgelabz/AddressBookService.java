package com.Bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService 
{ 
	// Load diver class and create connection
		public Connection getConnection() {
			Connection connection = null;
			try {
				String JDBCURL = "jdbc:mysql://localhost:3306/addressbook_service";
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(JDBCURL, "root", "root");
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Driver not loaded");
			}
			return connection;
		}

		// UC16 - retrieve contact
		public List<ContactInfo> retrieveContact() {
			List<ContactInfo> contactInfo = new ArrayList<>();
			try (Connection connection = getConnection()) {
				Statement statement = connection.createStatement();
				String sqlQuery = "select * from addressbook";
				ResultSet resultset = statement.executeQuery(sqlQuery);
				while (resultset.next()) {
					ContactInfo info = new ContactInfo();
					info.setId(resultset.getInt("id"));
					info.setFirstName(resultset.getString("firstname"));
					info.setLastName(resultset.getString("lastname"));
					info.setPhoneNumber(resultset.getString("phonenumber"));
					info.setEmail(resultset.getString("email"));
					info.setAddress(resultset.getString("address"));
					info.setCity(resultset.getString("city"));
					info.setState(resultset.getString("state"));
					info.setZip(resultset.getInt("zip"));
					contactInfo.add(info);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(contactInfo);
			return contactInfo;
		}

}
