package coffeeMachine.state;

import coffeeMachine.CoffeeMachine;
import coffeeMachine.beverages.Beverage;
import coffeeMachine.beverages.BeverageFactory;

public class ServeBeverage implements MachineState {

  private BeverageFactory beveragesFactory;

  public ServeBeverage(BeverageFactory beveragesFactory) {
    this.beveragesFactory = beveragesFactory;
  }

  @Override
  public void processRequest(String action, CoffeeMachine context) {
    Beverage beverage = beveragesFactory.selectBeverage(action, context.getEngine());
    String result = beverage.serve();
    context.showOnDisplay(result);
    context.setMachineState(new Idle());
    context.displayInvitation();
  }
}
