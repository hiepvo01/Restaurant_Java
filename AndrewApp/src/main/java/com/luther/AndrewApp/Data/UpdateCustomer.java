package com.luther.AndrewApp.Data;
import java.util.*;
import java.sql.*;
import java.time.LocalTime;

import com.mysql.jdbc.Driver;

import com.luther.AndrewApp.MainClasses.Information;

public class UpdateCustomer {
	String url = "jdbc:mysql://localhost:3306/staff";
	String username = "vohi01";
	String password = "Bangfish0911@";
  private Information info;
  private String email;
  private String memberStat;
  private int memberBal;

  public UpdateCustomer(Information info){
		this.info = info;
	}

	public void addCustomer(Information info){
		System.getProperty("java.class.path");
    // the mysql insert statement
		String query = " INSERT INTO `staff`.`customer` (`ID`, `Name`, `DOB`, `MembershipStatus`, `MembershipBalance`, `Email`)"
		+ " VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(url, this.username, this.password);
		PreparedStatement preparedStmt = conn.prepareStatement(query);) {
			// create the mysql insert preparedstatement
			preparedStmt.setInt (1, info.getID());
			preparedStmt.setString (2, info.getName());
			preparedStmt.setString (3, info.getDOB());
			preparedStmt.setInt(4, Integer.valueOf(info.getPosition()));
			preparedStmt.setInt(5, info.getBal());
			preparedStmt.setString(6, "email");
			// execute the preparedstatement
      preparedStmt.execute();
      System.out.println("Customer added to database");
		} catch (SQLException e) {
      System.out.println("Customer already added to database");
    }
	}

	public void removeCustomer(int id){
		System.getProperty("java.class.path");
    // the mysql insert statement
		String query = " delete from `staff`.`customer` where ID=?";

		try(Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement preparedStmt = conn.prepareStatement(query);)
    {
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
      System.out.println("Customer successfully removed");
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
		}
	}

	public void updateMemberBal(int bal, int id){
    System.getProperty("java.class.path");
    // the mysql insert statement
    String query = "update `staff`.`customer` set MembershipBalance = ? where id = ?";

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

  public void updateMembership(int membership, int id, String email){
    System.getProperty("java.class.path");
    // the mysql insert statement
    String query = "update `staff`.`customer` set MembershipStatus = ?, Email = ? where id = ?";

    try(Connection conn = DriverManager.getConnection(url, username, password);
    PreparedStatement preparedStmt = conn.prepareStatement(query);)
    {
      preparedStmt.setInt(1, membership);
      preparedStmt.setString(2, email);
      preparedStmt.setInt(3, id);

      // execute the preparedstatement
      if(membership == 0){
        System.out.println("You have successfully cancel your membership");
      }else if(membership == 1){
        System.out.println("You have successfully registered for membership");
      }
      preparedStmt.execute();
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
  }

	public String[] CusData(){
    String[] member = new String[2];
		System.getProperty("java.class.path");
		//CHECK EMPLOYEE DATA
		String check = " SELECT * FROM `staff`.`customer` ";
		try (Connection conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();) {
			System.out.println("Database connected!");
			System.out.println();
			System.out.format("%s, %s, %s, %s, %s, %s \n", "ID", "Name", "DOB", "MembershipStatus", "MembershipBalance", "Email");

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(check);
			// iterate through the java resultset
			while (rs.next())
			{
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String dob = rs.getString("DOB");
				String position = rs.getString("MembershipStatus");
				int balance = rs.getInt("MembershipBalance");
				String email = rs.getString("Email");

				// print the results
        System.out.format("%s, %s, %s, %s, %s, %s\n", id, name, dob, position, balance, email);
        member[0] = position;
        member[1] = String.valueOf(balance);
			}
			st.close();
		} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
    }
    return member;
	}
}
