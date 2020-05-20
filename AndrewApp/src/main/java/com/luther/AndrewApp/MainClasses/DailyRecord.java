package com.luther.AndrewApp.MainClasses;

import java.time.LocalDate;

import com.luther.AndrewApp.Data.UpdateStorage;

public class DailyRecord {
  private String customer;
  private String item;
  private int unitPrice;
  private int amt;
  private String date;


  public DailyRecord(){
    System.out.println("Default Daily Record Constructor");
  }

  public DailyRecord(String customer, String item, int unitPrice, int amt, String date){
    this.customer = customer;
    this.item = item;
    this.unitPrice = unitPrice;
    this.amt = amt;
    this.date = date;
  }

  public String getCustomer() {
    return this.customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getItem() {
    return this.item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public int getUnitPrice() {
    return this.unitPrice;
  }

  public void setUnitPrice(int unitPrice) {
    this.unitPrice = unitPrice;
  }

  public int getAmt() {
    return this.amt;
  }

  public void setAmt(int amt) {
    this.amt = amt;
  }

  public String getDate() {
    return String.valueOf(LocalDate.now());
  }

  public void setDate(String date) {
    this.date = date;
  }
}
