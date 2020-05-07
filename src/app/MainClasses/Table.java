package app.MainClasses;

public class Table extends Storage{
  public int size;
  public Storage storage;

  public Table(){
    System.out.println("Default Table constructor");
  }
  public Table(Storage s, int size){
    this.storage = s;
    this.size = size;
  }
  
  public int getSize() {
    return this.size;
  }

  public Storage getStorage() {
    return this.storage;
  }
}