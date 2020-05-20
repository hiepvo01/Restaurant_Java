package com.luther.AndrewApp.MainClasses;
import java.util.*;

public class Storage {
  public String name;
  public int amt;
  public int buyPrice;
  public int sellPrice;

  public Storage(){

  }

  public Storage(String name, int amt, int buyPrice, int sellPrice){
    this.name = name;
    this.amt = amt;
    this.buyPrice = buyPrice;
    this.sellPrice = sellPrice;
  }

  public String getName() {
    return this.name;
  }

  public int getAmt() {
    return this.amt;
  }

  public int getBuyPrice() {
    return this.buyPrice;
  }

  public int getSellPrice() {
    return this.sellPrice;
  }

  public void setSellPrice(int sellPrice) {
    this.sellPrice = sellPrice;
  }
}
