package com.Bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookService 
{ 
	// Load diver class and create connection
		public Connection getConnection() {
			Connection connection = null;
			try {
				String JDBCURL = "jdbc:mysql://localhost:3306/addressbook_service";
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(JDBCURL, "root", "123456");
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
		public void updateContactInfo(String firstname, int id) {
			try (Connection connection = getConnection()) {
				String query = "update addressbook set firstname = ? where id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, firstname);
				preparedStatement.setInt(2, id);
				int result = preparedStatement.executeUpdate();
				if (result >= 1) {
					System.out.println("contact updated");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// UC_18 retrieve contact of particular date period
		public List<ContactInfo> retrieveContactByDateRange() {
			List<ContactInfo> contactInfo = new ArrayList<>();
			try (Connection connection = getConnection()) {
				Statement statement = connection.createStatement();
				String sqlQuery = "select * from addressbook where date_added between '2022-01-01' and date(now())";
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
					info.setDate_added(resultset.getDate("date_added"));
					contactInfo.add(info);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(contactInfo);
			return contactInfo;
		}
		// UC_19 retrieve contact by city or state
		public Map<String, Integer> getCountByCity() {
			Map<String, Integer> countByCity = new HashMap<>();
			try (Connection connection = getConnection()) {
				Statement statement = connection.createStatement();
				String query = "select city,count(*) as count from addressbook group by city";
				ResultSet resultset = statement.executeQuery(query);
				while (resultset.next()) {
					String city = String.valueOf(resultset.getString("city"));
					Integer count1 = Integer.valueOf(resultset.getString("count"));
					countByCity.put(city, count1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(countByCity);
			return countByCity;
		}
		// UC_19 count by state
		public Map<String, Integer> getCountByState() {
			Map<String, Integer> countByState = new HashMap<>();
			try (Connection connection = getConnection()) {
				String query = "select state,count(*) as count from addressbook group by state";
				Statement statement = connection.createStatement();
				ResultSet resultset = statement.executeQuery(query);
				while (resultset.next()) {
					String state = String.valueOf(resultset.getString("state"));
					Integer count = Integer.valueOf(resultset.getString("count"));
					countByState.put(state, count);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(countByState);
			return countByState;
		}

		// UC_20 insert contact
		public void insertNewContact() {
			Connection connection = null;
			connection = getConnection();
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			try (Statement statement = connection.createStatement()) {
				String sql = format(
						"insert into addressbook(firstname,lastname,address,city,state,zip,phonenumber,email,ab_name,ab_type,date_added) "
								+ "values ('%s','%s','%s','%s','%s','%d','%s','%s','%s','%s','%s')",
						"test2", "demo", "NY", "Mumbai", "MH", 45677, "98877769", "test2@gmail.com", "AddressBook1",
						"Family", "2022-01-12");
				int rowAffected = statement.executeUpdate(sql);
				if (rowAffected >= 1) {
					System.out.println("contact added");
				}
			} catch (SQLException e) {
				System.out.println("exception" + e.getMessage());
			}
		}

		
		
}
