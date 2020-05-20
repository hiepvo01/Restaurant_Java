package com.luther.AndrewApp.MainClasses;
import java.time.LocalTime;
import java.util.*;

public class Information extends Balance {
  private String name;
  private String DOB;
  private int ID;
  private String position;

  public Information(){
    System.out.println("Default Information constructor");
  }

  public Information(int ID, String name, String DOB, String position, int balance){
    super(balance);
    this.name = name;
    this.DOB = DOB;
    this.ID = ID;
    this.position = position;
  }

	public String getName() {
    return this.name;
	}

	public String getDOB() {
    return this.DOB;
	}

	public int getID() {
    return this.ID;
	}

	public String getPosition() {
    return this.position;
  }
}
