package com.luther.AndrewApp.Data;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import com.mysql.jdbc.Driver;

import com.luther.AndrewApp.MainClasses.DailyRecord;
import com.luther.AndrewApp.MainClasses.Information;

public class UpdateOrders {
	String url = "jdbc:mysql://localhost:3306/staff";
	String username = "vohi01";
  String password = "Bangfish0911@";
  DailyRecord dr;

  public UpdateOrders(DailyRecord dr){
    this.dr = dr;
  }

	public void addOrder(){
		System.getProperty("java.class.path");
    // the mysql insert statement
		String query = " INSERT INTO `staff`.`orders` (`idorders`, `customer`, `item`, `unitprice`, `amount`, `date`)"
		+ " VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
		PreparedStatement preparedStmt = conn.prepareStatement(query);) {
			// create the mysql insert preparedstatement
			preparedStmt.setInt (1, this.getMaxId() + 1);
			preparedStmt.setString (2, dr.getCustomer());
			preparedStmt.setString (3, dr.getItem());
			preparedStmt.setInt(4, dr.getUnitPrice());
      preparedStmt.setInt(5, dr.getAmt());
			preparedStmt.setString(6, dr.getDate());
			// execute the preparedstatement
      preparedStmt.execute();
      System.out.println("Order added to database");
		} catch (SQLException e) {
      System.out.println("Order already added to database");
    }
	}

	public int getMaxId(){
		int n = 0;
		System.getProperty("java.class.path");
		//CHECK EMPLOYEE DATA
		String check = " SELECT * FROM `staff`.`orders` ";
		try (Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();) {

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(check);
			// iterate through the java resultset
			while (rs.next())
			{
				n = rs.getInt("idorders");
			}

			st.close();
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
		}
		return n;
	}

	public void removeOrder(int id){
		System.getProperty("java.class.path");
    // the mysql insert statement
		String query = " delete from `staff`.`orders` where idorders=?";

		try(Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement preparedStmt = conn.prepareStatement(query);)
    {
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
      System.out.println("Order successfully removed");
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
		}
	}

	public void ordersData(){
		System.getProperty("java.class.path");
		//CHECK EMPLOYEE DATA
		String check = " SELECT * FROM `staff`.`orders` ";
		try (Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();) {
			System.out.println("Database connected!");
			System.out.println();
			System.out.format("%s, %s, %s, %s, %s, %s \n", "idorders", "customer", "item", "unitprice", "amount", "date");

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(check);
			// iterate through the java resultset
			while (rs.next())
			{
				int id = rs.getInt("idorders");
				String cus = rs.getString("customer");
				String item = rs.getString("item");
				int price = rs.getInt("unitprice");
        int amt = rs.getInt("amount");
				String date = rs.getString("date");

				// print the results
        System.out.format("%s, %s, %s, %s, %s, %s\n", id, cus, item, price, amt, date);
			}
			st.close();
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
    }
	}
}
