package ch.bli.mez.model;

public class MainModel {
  private String name;
  
  public void updateName(String name) {
    this.name = name;
    System.out.println(name);
  }
  
  public String getName() {
    return name;
  }
}
