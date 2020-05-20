package com.luther.AndrewApp.UseCases;
import java.time.LocalTime;
import java.util.*;

import com.luther.AndrewApp.MainClasses.Employee;
import com.luther.AndrewApp.MainClasses.Information;
import com.luther.AndrewApp.MainClasses.Manager;

public class StaffControl {
  public static void main(String[] args){
    Information hiep = new Information(1, "Hiep", "03/11/99", "Manager", 6000);
    String[] hiep_shift = {"08:00:00", "17:00:00"};
    Manager Hiep = new Manager(hiep, hiep_shift, 0, 0);

    Information eric = new Information(2, "Eric", "04/10/99", "Employee", 3000);
    String[] eric_shift = {"09:00:00", "23:00:00"};
    Employee Eric = new Employee(eric, eric_shift, 0, 0);

    Information alex = new Information(3, "Alex", "06/09/99", "Employee", 2500);
    String[] alex_shift = {"09:00:00", "16:00:00"};
    Employee Alex = new Employee(alex, alex_shift, 0, 0);

    Hiep.addManagertoDB();

    // Add and Remove Employee UseCase, fire Employee by their ID
    // If the employee is already added, it will raise already added message
    Hiep.newEmployee(Eric);
    Hiep.newEmployee(Alex);
    System.out.println();
    Hiep.fireEmployee(372);
    System.out.println();

    // Clock In and Clock Out UseCase
    // You will get warning if you clock in early or clock in late
    // get Dates function is to check the dates you worked in the month
    Eric.clockIn();
    System.out.println(Eric.getDates());
    Eric.clockIn();
    Eric.clockIn();
    System.out.println();

    // Alex haven't clocked in but he still clock out so he would get a message and his date for today would not be registered
    Alex.clockIn();
    Alex.clockOut();
    System.out.println(Alex.getDates());
    System.out.println();

    // Manager can check general employee data
    Hiep.checkEmpData();
  }
}
