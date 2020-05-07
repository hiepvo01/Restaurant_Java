package app.MainClasses;

public class Food extends Storage{
  public String expire;
  public Storage storage;

  public Food(){
    System.out.println("Default Food constructor");
  }
  public Food(Storage s, String expire){
    this.storage = s;
    this.expire = expire;
  }
  
  public String getExpire() {
    return this.expire;
  }

  public Storage getStorage() {
    return this.storage;
  }
}