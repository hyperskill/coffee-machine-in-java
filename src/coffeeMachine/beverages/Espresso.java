package coffeeMachine.beverages;

import coffeeMachine.engine.CoffeeEngine;
import coffeeMachine.engine.CoffeeEngineStateFormatter;

public class Espresso implements Beverage {

  private static final int WATER_NEEDED = 250;
  private static final int COFFEE_NEEDED = 16;
  private static final int CUPS_NEEDED = 1;
  private static final int COST_OF_BEVERAGE = 4;

  private final CoffeeEngineStateFormatter formatter;

  private CoffeeEngine engine;

  public Espresso(CoffeeEngine engine) {
    this.engine = engine;
    this.formatter = new CoffeeEngineStateFormatter(engine);
  }

  @Override
  public String serve() {
    if (isEnoughResources()) {
      engine.setWater(engine.getWater() - WATER_NEEDED);
      engine.setCoffee(engine.getCoffee() - COFFEE_NEEDED);
      engine.setCups(engine.getCups() - CUPS_NEEDED);
      engine.setMoney(engine.getMoney() + COST_OF_BEVERAGE);
      return SUCCESS_MESSAGE;
    } else {
      return formatter
          .printResoucesNotInRange(WATER_NEEDED, 0, COFFEE_NEEDED, CUPS_NEEDED);
    }
  }

  private boolean isEnoughResources() {
    return engine.getWater() - WATER_NEEDED >= 0 && engine.getCoffee() - COFFEE_NEEDED >= 0
        && engine.getCups() - CUPS_NEEDED >= 0;
  }
}
