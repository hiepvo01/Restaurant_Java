package com.luther.AndrewApp.UseCases;

import com.luther.AndrewApp.MainClasses.Customer;
import com.luther.AndrewApp.MainClasses.Information;
import com.luther.AndrewApp.MainClasses.Restaurant;

public class CustomerControl {
  public static void main(String[] args){
    Restaurant res = new Restaurant();
    System.out.println();
    System.out.println(res.table2);
    // Create Customer
    Information info = new Information(1, "Jordan", "09/08/2000", "0", 0);
    Customer Jordan = new Customer(info);
    // Customer can register membership to get points from paying bills
    Jordan.registerMembership("jordon01@luther.edu");
    Jordan.getCusDB().CusData();
    System.out.println();
    // Customer can reserve table for a group. They can't order ultil they reserved seats
    Jordan.order("Spaghetti", 4);
    Jordan.reserveTable(res, 10);
    // Now Jordan can order
    Jordan.order("Spaghetti", 4);
    Jordan.order("Coke", 4);
    res.store.ItemData();
    System.out.println();
    // Customer can pay with their membership points too, each point worths 20 cents
    // After the payment, the number of available tables return
    // Customer can also tip
    Jordan.getBill();
    Jordan.pay(res, 8);
    Jordan.tip(res, 15);
    res.store.ItemData();
    Jordan.getCusDB().CusData();
    // The total money paid by customers will be hold in Restaurant Balance
    System.out.print("Restaurant Balance: ");
    System.out.print(res.getBalance());
    // THe orders data have been recorded in database
    Jordan.uo.ordersData();
  }
}
