package com.luther.AndrewApp.MainClasses;
import java.util.*;

import com.luther.AndrewApp.Data.UpdateStorage;

public class Restaurant{
  public int table2;
  public int table4;
  public int balance = 0;

  private Table[] table = new Table[3];
  private Food[] menu = new Food[4];
  public UpdateStorage store = new UpdateStorage();
  private Storage t2 = new Storage("Table2", 5, 15, 25);
  private Storage t4 = new Storage("Table4", 10, 20, 35);
  private Storage spa = new Storage("Spaghetti", 30, 3, 5);
  private Storage ste = new Storage("Steak", 40, 5, 8);
  private Storage coke = new Storage("Coke", 40, 1, 3);

  public Restaurant(){
    System.out.println("Default Restaurant constructor");
    this.table[0] = new Table(this.t2, 2);
    store.addItemtoDB(this.t2, 2, "");
    this.table[1] = new Table(this.t4, 4);
    store.addItemtoDB(this.t4, 4, "");
    this.menu[0] = new Food(this.spa, "2021-05-06");
    store.addItemtoDB(this.spa, 0, "2021-05-06");
    this.menu[1] = new Food(this.ste, "2021-07-02");
    store.addItemtoDB(this.ste, 0, "2021-07-02");
    this.menu[2] = new Food(this.coke, "2022-07-02");
    store.addItemtoDB(this.coke, 0, "2022-07-02");
    this.table2 = table[0].storage.amt;
    this.table4 = table[1].storage.amt;
    store.ItemData();
  }

  public int getTable2() {
    return this.store.getAmount("Table2");
  }

  public int getTable4() {
    return this.store.getAmount("Table4");
  }
  public int getBalance() {
    return this.balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
}
