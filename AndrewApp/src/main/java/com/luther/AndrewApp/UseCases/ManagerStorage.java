package com.luther.AndrewApp.UseCases;
import java.time.LocalTime;
import java.util.*;

import com.luther.AndrewApp.MainClasses.Information;
import com.luther.AndrewApp.MainClasses.Manager;
import com.luther.AndrewApp.MainClasses.Restaurant;


// 2 The manager can buy and sell products from the restaurant storage
public class ManagerStorage {
  public static void main(String[] args){
    // Default restaurant with 2 table types, 2 foods and 1 beverage
    Restaurant res = new Restaurant();
    System.out.println();

    Information hiep = new Information(1, "Hiep", "03/11/99", "Manager", 6000);
    String[] hiep_shift = {"08:00:00", "17:00:00"};
    Manager Hiep = new Manager(hiep, hiep_shift, 0, 0);
    Hiep.addManagertoDB();
    System.out.println();

    // When the manager buy an item to restock the restaurant, his/her balance will be reduced by the total price and the storage data of that item will increase accordingly
    Hiep.buy("Spaghetti", 7);
    System.out.print("Hiep remaining balance: ");
    System.out.println(Hiep.getInfo().getBal());
    System.out.println();
    res.store.ItemData();
    System.out.println();

    // When the manager want to sell some of his stock, his/her balance will increase by the total sell price and the number of items will be reduced
    Hiep.sell("Steak", 5);
    System.out.print("Hiep remaining balance: ");
    System.out.println(Hiep.getInfo().getBal());
    System.out.println();
    res.store.ItemData();
    System.out.println();
  }
}
