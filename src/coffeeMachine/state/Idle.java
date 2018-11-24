package coffeeMachine.state;

import coffeeMachine.CoffeeMachine;
import coffeeMachine.beverages.StandartBeverages;

public class Idle implements MachineState {

  private static final String BUY = "buy";
  private static final String FILL = "fill";
  private static final String TAKE = "take";
  private static final String EXIT = "exit";
  private static final String REMAINING = "remaining";

  @Override
  public void processRequest(String action, CoffeeMachine context) {
    switch (action) {
      case BUY:
        context.triggerBuyProcess();
        context.setMachineState(new ServeBeverage(StandartBeverages.Factory));
        break;
      case FILL:
        context.triggerFillProcess();
        context.setMachineState(new FillWater());
        break;
      case TAKE:
        context.withdrawMoney();
        context.displayInvitation();
        break;
      case EXIT:
        context.terminate();
        break;
      case REMAINING:
        context.displayState();
        context.displayInvitation();
        break;
    }
  }
}
