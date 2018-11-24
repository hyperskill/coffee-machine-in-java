package coffeeMachine.beverages;

import coffeeMachine.engine.CoffeeEngine;

public enum StandartBeverages implements BeverageFactory {
  Factory {
    @Override
    public Beverage selectBeverage(String option, CoffeeEngine engine) {
      switch (option) {
        case "1":
          return new Espresso(engine);
        case "2":
          return new Latte(engine);
        case "3":
          return new Capuccino(engine);
      }
      return null;
    }
  }
}
