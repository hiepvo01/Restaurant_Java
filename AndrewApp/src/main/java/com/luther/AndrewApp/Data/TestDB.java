package com.luther.AndrewApp.Data;
import java.sql.*;
import java.util.*;
import com.mysql.jdbc.Driver;

public class TestDB
{
  public static void main(String[] args)
  {
		System.getProperty("java.class.path");
		System.out.println("Loading driver...");

		String url = "jdbc:mysql://localhost:3306/staff";
		String username = "vohi01";
		String password = "Bangfish0911@";

		// the mysql insert statement
		String query = " INSERT INTO `staff`.`staff_info` (`ID`, `Name`, `DOB`, `Position`, `Balance`)"
		+ " VALUES (?, ?, ?, ?, ?)";

		System.out.println("Connecting database...");

		//ADD Employee
		try (Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement preparedStmt = conn.prepareStatement(query);) {
			System.out.println("Database connected!");
			// create the mysql insert preparedstatement
			Random rand = new Random();
			preparedStmt.setInt (1, rand.nextInt(1000));
			preparedStmt.setString (2, "asdfasdf");
			preparedStmt.setString (3, "4/5/1999");
			preparedStmt.setString(4, "manager");
			preparedStmt.setInt    (5, 5000);
			// execute the preparedstatement
      preparedStmt.execute();
			System.out.println("Database updated successfully ");
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
		}

		//REMOVE Employee
		String rem = " delete from `staff`.`staff_info` where id=?";
		try (Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement preparedStmt = conn.prepareStatement(rem);) {
			System.out.println("Database connected!");
			preparedStmt.setInt(1, 2);

      // execute the preparedstatement
      preparedStmt.execute();

			System.out.println("Database updated successfully ");
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
		}

		//CHECK EMPLOYEE DATA
		String check = " SELECT * FROM `staff`.`staff_info` ";
		try (Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();) {
			System.out.println("Database connected!");

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

        // print the results
        System.out.format("%s, %s, %s, %s, %s\n", id, name, dob, position, balance);
      }
      st.close();
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}
