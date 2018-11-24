package coffeeMachine.state;

import coffeeMachine.CoffeeMachine;

class FillBeans implements MachineState {

  @Override
  public void processRequest(String action, CoffeeMachine context) {
    context.getEngine().setCoffee(context.getEngine().getCoffee() + Integer.valueOf(action));
    context.setMachineState(new FillCups());
    context.showOnDisplay("Write how many disposable cups of coffee do you want to add: ");
  }
}
