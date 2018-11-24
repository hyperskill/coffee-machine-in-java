package coffeeMachine.beverages;

import coffeeMachine.engine.CoffeeEngine;

public interface BeverageFactory {

  Beverage selectBeverage(String option, CoffeeEngine engine);
}
