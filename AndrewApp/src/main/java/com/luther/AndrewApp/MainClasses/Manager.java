package com.luther.AndrewApp.MainClasses;
import java.time.LocalTime;
import java.util.*;

import com.luther.AndrewApp.Data.UpdateStaff;
import com.luther.AndrewApp.Data.UpdateStorage;

public class Manager{
  private String[] shift;
  private Information info;
  private int daysInMonth;
  private int warnings;

  private UpdateStaff manager;
  public UpdateStorage store = new UpdateStorage();

  public Manager(){
    System.out.println("Default Manager Constructor");
  }

  public Manager(Information info, String[] shift, int daysInMonth, int warnings){
    this.info = info;
    this.shift = shift;
    this.daysInMonth = daysInMonth;
    this.warnings = warnings;
    manager = new UpdateStaff(this.info);
  }

  public String addManagertoDB(){
    String mess = "";
    try{
      manager.addStaff(this.info, this.shift, this.daysInMonth, this.warnings);
      mess = "Manager added to database";
    }
    catch (Exception e)
    {
      mess = "The manager is already added to the database";
      System.out.println("The manager is already added to the database");
        }
    return mess;
  }

  public void newEmployee(Employee a){
    try{
      manager.addStaff(a.getInfo(), a.getShift(), a.getDaysInMonth(), a.getWarnings());
    }
    catch (Exception e)
    {
      System.out.println("The employee is already added to the database");
		}
  }

  public void fireEmployee(int emp_id){
    manager.removeStaff(emp_id);
  }

  public String checkEmpData(){
    String mess = "";
    mess = manager.empData();
    return mess;
  }

  public void buy(String name, int amt){
    boolean con = true;
    System.out.print("Price Buy " + name + ": ");
    System.out.println(store.getPrice(name, "Buy"));
    if(this.info.getBal() < store.getPrice(name, "Buy") * amt){
      System.out.println("You don't have enough money to buy these items");
      con = false;
    }
    if(con ==true){
      this.info.reduceBal(store.getPrice(name, "Buy") * amt);
      manager.updateBal(info.getBal(), info.getID());
      store.updateItemAmt(name, store.getAmount(name) + amt);
    }
  }

  public void sell(String name, int amt){
    System.out.print("Price Sell " + name + ": ");
    System.out.println(store.getPrice(name, "Sell"));
    boolean con = true;
    if(store.getAmount(name) - amt < 0){
      System.out.println("There is not emough items to sell");
      con = false;
    }
    if(con ==true){
      this.info.addBal(store.getPrice(name, "Sell") * amt);
      manager.updateBal(info.getBal(), info.getID());
      store.updateItemAmt(name, store.getAmount(name) - amt);
    }
  }

  public Information getInfo() {
    return this.info;
  }

  public int getDaysInMonth() {
    return this.daysInMonth;
  }

  public String getShifts() {
    String shifts = "";
    for(String i: this.shift){
      shifts += i;
      shifts += " ";
    }
    return shifts;
  }
}
