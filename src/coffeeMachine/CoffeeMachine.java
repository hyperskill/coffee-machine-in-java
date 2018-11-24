package coffeeMachine;

import coffeeMachine.engine.CoffeeEngine;
import coffeeMachine.engine.CoffeeEngineStateFormatter;
import coffeeMachine.engine.Display;
import coffeeMachine.state.MachineState;

/**
 * Coffee machine able to handle input and contains engine state.
 */
public class CoffeeMachine {

  private static final String INVITATION_STRING = "Write action (buy, fill, take, remaining, exit): ";
  private static final String BUY_INVITATION = "\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ";

  private final CoffeeEngine coffeeEngine;
  private final CoffeeEngineStateFormatter stateFormatter;
  private final Display display;

  private MachineState machineState;
  private boolean terminated;

  public CoffeeMachine(CoffeeEngine coffeeEngine, Display display) {
    this.coffeeEngine = coffeeEngine;
    this.display = display;
    this.machineState = MachineState.defaultState();
    this.stateFormatter = new CoffeeEngineStateFormatter(coffeeEngine);
  }

  public void process(String input) {
    machineState.processRequest(input, this);
  }

  public void setMachineState(MachineState machineState) {
    this.machineState = machineState;
  }

  public boolean isTerminated() {
    return terminated;
  }

  public void showOnDisplay(String message) {
    display.displayMessage(message);
  }

  public void withdrawMoney() {
    showOnDisplay(stateFormatter.printWithdraw());
    coffeeEngine.setMoney(0);
  }

  public void terminate() {
    terminated = true;
  }

  public void displayInvitation() {
    showOnDisplay(INVITATION_STRING);
  }

  public void displayState() {
    showOnDisplay(stateFormatter.printState());
  }

  public CoffeeEngine getEngine() {
    return coffeeEngine;
  }

  public void triggerBuyProcess() {
    showOnDisplay(BUY_INVITATION);
  }

  public void triggerFillProcess() {
    showOnDisplay("Write how many ml of water do you want to add: ");
  }
}
