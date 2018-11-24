package coffeeMachine.state;

import coffeeMachine.CoffeeMachine;

class FillCups implements MachineState {

  @Override
  public void processRequest(String action, CoffeeMachine context) {
    context.getEngine().setCups(context.getEngine().getCups() + Integer.valueOf(action));
    context.setMachineState(new Idle());
    context.showOnDisplay("\n");
    context.displayInvitation();
  }
}
