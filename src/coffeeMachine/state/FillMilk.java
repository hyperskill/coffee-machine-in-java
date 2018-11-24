package coffeeMachine.state;


import coffeeMachine.CoffeeMachine;

class FillMilk implements MachineState {

  @Override
  public void processRequest(String action, CoffeeMachine context) {
    context.getEngine().setMilk(context.getEngine().getMilk() + Integer.valueOf(action));
    context.setMachineState(new FillBeans());
    context.showOnDisplay("Write how many grams of coffee beans do you want to add: ");
  }
}
