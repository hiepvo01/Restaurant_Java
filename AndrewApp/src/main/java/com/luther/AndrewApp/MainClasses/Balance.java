package com.luther.AndrewApp.MainClasses;
import java.util.*;

public class Balance {
  private int bal;

  public Balance(){
    System.out.println("Default Balance constructor");
  }

  public Balance(int bal){
    this.bal = bal;
  }

  public void addBal(int amt){
    bal += amt;
  }

  public void reduceBal(int amt){
    bal -= amt;
  }

	public int getBal() {
		return this.bal;
	}

	public void setBal(int bal) {
		this.bal = bal;
  }
}
