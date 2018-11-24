package coffeeMachine.engine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CoffeeEngineStateFormatter {

  private CoffeeEngine coffeeEngine;

  public CoffeeEngineStateFormatter(CoffeeEngine coffeeEngine) {
    this.coffeeEngine = coffeeEngine;
  }

  public String printState() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream stringWriter = new PrintStream(out);
    stringWriter.printf("\nThe coffee machine has:\n");
    stringWriter.printf("%d of water\n", coffeeEngine.getWater());
    stringWriter.printf("%d of milk\n", coffeeEngine.getMilk());
    stringWriter.printf("%d of coffee beans\n", coffeeEngine.getCoffee());
    stringWriter.printf("%d of disposable cups\n", coffeeEngine.getCups());
    stringWriter.printf("%d of money\n\n", coffeeEngine.getMoney());
    return new String(out.toByteArray());
  }

  public String printWithdraw() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream stringWriter = new PrintStream(out);
    stringWriter.printf("\nI gave you $%d\n\n", coffeeEngine.getMoney());
    return new String(out.toByteArray());
  }

  public String printResoucesNotInRange(int water, int milk, int coffee, int cups) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream stringWriter = new PrintStream(out);
    if (coffeeEngine.getWater() - water < 0) {
      stringWriter.printf("Sorry, not enough water!\n");
    }
    if (coffeeEngine.getMilk() - milk < 0) {
      stringWriter.printf("Sorry, not enough milk!\n");
    }
    if (coffeeEngine.getCoffee() - coffee < 0) {
      stringWriter.printf("Sorry, not enough coffee beans!\n");
    }
    if (coffeeEngine.getCups() - cups < 0) {
      stringWriter.printf("Sorry, not enough cups!\n");
    }
    stringWriter.printf("\n");
    return new String(out.toByteArray());
  }
}
