package coffeeMachine;

import coffeeMachine.engine.CoffeeEngine;
import coffeeMachine.engine.Display;
import coffeeMachine.engine.StdIoDisplay;
import java.util.*;

/**
 * Coffee machine runner attached to stdin/stdout.
 */
class Main {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      CoffeeEngine coffeeEngine = new CoffeeEngine(400, 540, 120, 9, 550);
      Display display = new StdIoDisplay();
      CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeEngine, display);
      coffeeMachine.displayInvitation();
      while (!coffeeMachine.isTerminated()) {
        coffeeMachine.process(sc.nextLine());
      }
    }
  }
}