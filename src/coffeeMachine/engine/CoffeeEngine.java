package coffeeMachine.engine;


public class CoffeeEngine {

  private int water;
  private int milk;
  private int coffee;
  private int cups;
  private int money;

  public CoffeeEngine(int water, int milk, int coffee, int cups, int money) {
    this.water = water;
    this.milk = milk;
    this.coffee = coffee;
    this.cups = cups;
    this.money = money;
  }

  public int getWater() {
    return water;
  }

  public void setWater(int water) {
    this.water = water;
  }

  public int getMilk() {
    return milk;
  }

  public void setMilk(int milk) {
    this.milk = milk;
  }

  public int getCoffee() {
    return coffee;
  }

  public void setCoffee(int coffee) {
    this.coffee = coffee;
  }

  public int getCups() {
    return cups;
  }

  public void setCups(int cups) {
    this.cups = cups;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }
}
