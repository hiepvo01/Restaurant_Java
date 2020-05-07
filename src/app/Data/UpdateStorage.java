package app.Data;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import com.mysql.jdbc.Driver;

import app.MainClasses.Storage;

public class UpdateStorage {
	String url = "jdbc:mysql://localhost:3306/staff";
	String username = "vohi01";
	String password = "Bangfish0911@";
	private Storage store;

  public UpdateStorage(){

	}
	
	public void addItemtoDB(Storage store, int size, String expire){
		System.getProperty("java.class.path");
    // the mysql insert statement
		String query = " INSERT INTO `staff`.`storage` (`Name`, `Amount`, `BuyPrice`, `SellPrice`, `Size`, `ExpireDate`)"
		+ " VALUES (?, ?, ?, ?, ?, ?)";
				
		try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
		PreparedStatement preparedStmt = conn.prepareStatement(query);) {
			// create the mysql insert preparedstatement
			preparedStmt.setString (1, store.getName());
			preparedStmt.setInt (2, store.getAmt());
			preparedStmt.setInt (3, store.getBuyPrice());
			preparedStmt.setInt (4, store.getSellPrice());
			preparedStmt.setInt (5, size);
			preparedStmt.setString(6, expire);

			// execute the preparedstatement
      preparedStmt.execute();
		} catch (SQLException e) {
				System.out.println("The Item is already included");
		}
	}

	public void updateItemAmt(String name, int amount){
    System.getProperty("java.class.path");
		// the mysql insert statement
    String query = "update `staff`.`storage` set Amount = ? where name = ?";
    
    try(Connection conn = DriverManager.getConnection(url, username, password);
    PreparedStatement preparedStmt = conn.prepareStatement(query);)
    { 
      preparedStmt.setInt(1, amount);
			preparedStmt.setString(2, name);
  
      // execute the preparedstatement
      preparedStmt.execute();
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
	}
	
	public int getPrice(String name, String action){
		int price = 0;
		System.getProperty("java.class.path");
		//CHECK EMPLOYEE DATA
		String check = " SELECT * FROM `staff`.`storage` ";
		try (Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();) {

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(check);
			// iterate through the java resultset
			while (rs.next())
			{
				String n = rs.getString("Name");
				if(n.equals(name)){
					price = rs.getInt(action + "Price");
				}
			}
			
			st.close();
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
		}
		if(price==0){
			System.out.println("Cannot find appropriate price for product, return 0 by default");
		}
		return price;
	}

	public int getAmount(String name){
		int amt = 0;
		System.getProperty("java.class.path");
		//CHECK EMPLOYEE DATA
		String check = " SELECT * FROM `staff`.`storage` ";
		try (Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();) {

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(check);
			// iterate through the java resultset
			while (rs.next())
			{
				String n = rs.getString("Name");
				if(n.equals(name)){
					amt = rs.getInt("Amount");
				}
			}
			
			st.close();
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
		}
		if(amt==0){
			System.out.println("Cannot find appropriate price for product, return 0 by default");
		}
		return amt;
	}

	public void ItemData(){
		System.getProperty("java.class.path");
		//CHECK EMPLOYEE DATA
		String check = " SELECT * FROM `staff`.`storage` ";
		try (Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();) {
			System.out.println("Database connected!");
			System.out.println();
			System.out.format("%s, %s, %s, %s, %s, %s\n", "Name", "Amount", "BuyPrice", "SellPrice", "Size", "ExpireDate");
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(check);
			// iterate through the java resultset
			while (rs.next())
			{
				String name = rs.getString("Name");
				int amt = rs.getInt("Amount");
				int buy = rs.getInt("BuyPrice");
				int sell = rs.getInt("SellPrice");
				int size = rs.getInt("Size");
				String expire = rs.getString("ExpireDate");
				
				// print the results
				System.out.format("%s, %s, %s, %s, %s, %s\n", name, amt, buy, sell, size, expire);
			}
			
			st.close();
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}