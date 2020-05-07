package app.MainClasses;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import app.Data.UpdateStaff;

public class Employee{
  private Information info;
  private String[] shift;
  private int daysInMonth;
  private UpdateStaff Emp;

  private LocalDate[] dates;
  private int warnings;

  public Employee(){
    System.out.println("Default Employee Constructor");
  }
  
  public Employee(Information info, String[] shift, int daysInMonth, int warnings){
    this.info = info;
    this.shift = shift;
    this.daysInMonth = daysInMonth;
    this.dates = new LocalDate[31];
    Emp = new UpdateStaff(info);
    this.warnings = warnings;
  }
  
  public void clockIn(){
    boolean proceed = true;

    for (LocalDate element : dates) {
      if (String.valueOf(element).equals(String.valueOf(LocalDate.now()))) {
        System.out.println("You have already clockedIn");
        proceed = false;
      }
    }

    LocalTime now = LocalTime.now();
    Boolean closed = now.isAfter(LocalTime.parse(shift[1]));
    if(closed == true){
      System.out.println("Your shift is over, you cannot clock in");
      proceed = false;
    }

    if(proceed == true){
      this.daysInMonth += 1;
      Boolean isLate = now.isAfter(LocalTime.parse(shift[0]));
      if(isLate == true){
        System.out.println("You have clocked in late today");
        warnings += 1;
      }
      
      Emp.daysWorked(daysInMonth, warnings, this.info.getID());
  
      int counter = 0;
      for (int i = 0; i < dates.length; i ++){
        if (dates[i] != null)
        counter ++;
      }
      dates[counter] = LocalDate.now();
    }
  }
  
  public void clockOut(){
    boolean proceed = false;

    for (LocalDate element : dates) {
      if (String.valueOf(element).equals(String.valueOf(LocalDate.now()))) {
        proceed = true;
      }
    }

    if(proceed == true){      
      LocalTime now = LocalTime.now();
      Boolean isLate = now.isAfter(LocalTime.parse(shift[1]));
      if(isLate == false){
        System.out.println("You have clocked out early today");
        warnings += 1;
      }
      Emp.daysWorked(daysInMonth, warnings, this.info.getID());
    } else {
      System.out.println("You haven't clocked in today");
    }
  }
  
  public Information getInfo() {
    return this.info;
  }
  
  public String[] getShift() {
    return this.shift;
  }
  
  public int getDaysInMonth() {
    return this.daysInMonth;
  }

  public String getDates() {
    String dates = "";
    for(LocalDate d:this.dates){
      dates += String.valueOf(d);
      dates += " | ";
    }
    return dates;
  }

  public int getWarnings() {
    return this.warnings;
  }
}