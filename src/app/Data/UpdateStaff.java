package app.Data;
import java.util.*;
import java.sql.*;
import java.time.LocalTime;

import com.mysql.jdbc.Driver;

import app.MainClasses.Information;

public class UpdateStaff {
	String url = "jdbc:mysql://localhost:3306/staff";
	String username = "vohi01";
	String password = "Bangfish0911@";
	private Information info;

  public UpdateStaff(Information info){
		this.info = info;		
	}
	
	public void addStaff(Information info, String[] shifts, int daysInMonth, int warnings){
		System.getProperty("java.class.path");
    // the mysql insert statement
		String query = " INSERT INTO `staff`.`staff_info` (`ID`, `Name`, `DOB`, `Position`, `Balance`, `ClockIn`, `ClockOut`, `DaysInMonth`, `Warnings`)"
		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
		try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
		PreparedStatement preparedStmt = conn.prepareStatement(query);) {
			System.out.println("Database connected!");
			// create the mysql insert preparedstatement
			preparedStmt.setInt (1, info.getID());
			preparedStmt.setString (2, info.getName());
			preparedStmt.setString (3, info.getDOB());
			preparedStmt.setString(4, info.getPosition());
			preparedStmt.setInt(5, info.getBal());
			preparedStmt.setString(6, shifts[0]);
			preparedStmt.setString(7, shifts[1]);
			preparedStmt.setInt(8, daysInMonth);
			preparedStmt.setInt(9, warnings);
			// execute the preparedstatement
      preparedStmt.execute();
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void removeStaff(int id){
		System.getProperty("java.class.path");
    // the mysql insert statement
		String query = " delete from `staff`.`staff_info` where id=?";
		
		try(Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement preparedStmt = conn.prepareStatement(query);)
    { 
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
      System.out.println("Employee successfully removed");
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
		}		
	}

	public void daysWorked(int days, int warnings, int id){
    System.getProperty("java.class.path");
    // the mysql insert statement
    String query = "update `staff`.`staff_info` set DaysInMonth = ?, Warnings = ? where id = ?";
    
    try(Connection conn = DriverManager.getConnection(url, username, password);
    PreparedStatement preparedStmt = conn.prepareStatement(query);)
    { 
      preparedStmt.setInt(1, days);
			preparedStmt.setInt(2, warnings);
			preparedStmt.setInt(3, id);
  
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

	public void updateBal(int bal, int id){
    System.getProperty("java.class.path");
    // the mysql insert statement
    String query = "update `staff`.`staff_info` set Balance = ? where id = ?";
    
    try(Connection conn = DriverManager.getConnection(url, username, password);
    PreparedStatement preparedStmt = conn.prepareStatement(query);)
    { 
      preparedStmt.setInt(1, bal);
			preparedStmt.setInt(2, id);
  
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
	
	public void empData(){
		System.getProperty("java.class.path");
		//CHECK EMPLOYEE DATA
		String check = " SELECT * FROM `staff`.`staff_info` ";
		try (Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();) {
			System.out.println("Database connected!");
			System.out.println();
			System.out.format("%s, %s, %s, %s, %s, %s, %s, %s, %s\n", "ID", "Name", "DOB", "Position", "Balance", "ClockIn", "ClockOut", "DaysInMonth", "Warnings");

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(check);
			// iterate through the java resultset
			while (rs.next())
			{
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String dob = rs.getString("DOB");
				String position = rs.getString("Position");
				int balance = rs.getInt("Balance");
				String clockIn = rs.getString("ClockIn");
				String clockOut = rs.getString("ClockOut");
				int daysInMonth = rs.getInt("DaysInMonth");
				int warnings = rs.getInt("Warnings");
				
				// print the results
				System.out.format("%s, %s, %s, %s, %s, %s, %s, %s, %s\n", id, name, dob, position, balance, clockIn, clockOut, daysInMonth, warnings);
			}
			
			st.close();
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}