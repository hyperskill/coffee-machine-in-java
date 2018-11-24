package coffeeMachine.state;

import coffeeMachine.CoffeeMachine;

public class FillWater implements MachineState {

  @Override
  public void processRequest(String action, CoffeeMachine context) {
    context.getEngine().setWater(context.getEngine().getWater() + Integer.valueOf(action));
    context.setMachineState(new FillMilk());
    context.showOnDisplay("Write how many ml of milk do you want to add: ");
  }
}
