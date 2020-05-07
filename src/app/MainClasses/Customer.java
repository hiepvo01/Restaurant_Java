package app.MainClasses;

import java.time.LocalDate;

import app.Data.UpdateCustomer;
import app.Data.UpdateOrders;
import app.Data.UpdateStorage;

public class Customer {
  private Information info;
  private UpdateStorage store = new UpdateStorage();
  private int t4=0;
  private int t2=0;
  private String bill = "";
  private int price = 0;
  private UpdateCustomer cusDB;
  private DailyRecord dr;
  public UpdateOrders uo;

  public Customer(Information info){
    this.info = info;
    this.cusDB = new UpdateCustomer(info);
    cusDB.addCustomer(info);
  }
  
  public void order(String food, int amt){
    if(store.getAmount(food) - amt >= 0){
      if(this.t2*2 + this.t4*4 > 0){
        store.updateItemAmt(food, store.getAmount(food) - amt);
        this.price += store.getPrice(food, "Sell") * amt;
        this.bill += amt + " " + food + ": " + store.getPrice(food, "Sell") * amt + "\n";
        // The paid order will be recorded to database
        dr = new DailyRecord(this.info.getName(), food, store.getPrice(food, "Sell"), amt,"");
        uo = new UpdateOrders(dr);
        uo.addOrder();
      } else {
        System.out.println("You can't order if you haven't reserve your seats");
      }
    } else {
      System.out.println("The Store only have: " + store.getAmount(food) + " of this item left");
    }
  }

  public void registerMembership(String email){
    cusDB.updateMembership(1, this.info.getID(), email);
  }
  
  public void cancelMembership(String email){
    cusDB.updateMembership(0, this.info.getID(), email);
  }

  // When the customer with membership pay with points, each point worth 20 cents
  // The membership points will increase base on the value of the bill
  public void pay(Restaurant res, int points){
    this.bill += "Total Price: " + price;
    String[] membership = this.cusDB.CusData();
    if(membership[0].equals("1") && Integer.valueOf(membership[1]) > points){
      this.price -= 0.2 * points;
      this.cusDB.updateMemberBal(Integer.valueOf(membership[1]) - points, this.info.getID());
    }
    else {
      System.out.println("You don't have membership status or you don't have enough membership points");
    }
    String[] membership1 = this.cusDB.CusData();
    if(price > 50){
      this.cusDB.updateMemberBal(Integer.valueOf(membership1[1]) + 20, this.info.getID());
    }else if(price > 30){
      this.cusDB.updateMemberBal(Integer.valueOf(membership1[1]) + 10, this.info.getID());
    }else if (price > 15){
      this.cusDB.updateMemberBal(Integer.valueOf(membership1[1]) + 5, this.info.getID());
    }
    System.out.println(this.bill);
    int add = res.getBalance();
    res.setBalance(add += this.price);
    int resT2 = res.getTable2() + this.t2;
    int resT4 = res.getTable4() + this.t4;
    res.store.updateItemAmt("Table2", resT2);
    res.store.updateItemAmt("Table4", resT4);
  }

  public void tip(Restaurant res, int percentage){
    int add = res.getBalance();
    res.setBalance(add += this.price * percentage / 100);
    System.out.format("Thank you for tipping %s percent: %s dollars", percentage, this.price * percentage / 100);
  }
  
  public void reserveTable(Restaurant res, int customers){
    if(customers % 4 == 0){
      this.t4 = customers/4;
    }
    else if(customers < 4){
      if(customers == 3){
        this.t2=2;
      } else{
        this.t2=1;
      }
    }
    else{
      this.t4 = customers/4;
      customers -= t4*4;
      if(customers == 3){
        this.t2=2;
      }else{
        this.t2=1;
      }
    }
    System.out.println("Tables for 2: " + t2);
    System.out.println("Tables for 4: " + t4); 
    int resT2 = res.getTable2() - t2;
    int resT4 = res.getTable4() - t4;
    res.store.updateItemAmt("Table2", resT2);
    res.store.updateItemAmt("Table4", resT4);
  }

  public String getBill() {
    return this.bill;
  }

  public int getPrice() {
    return this.price;
  }

  public UpdateCustomer getCusDB() {
    return this.cusDB;
  }
}