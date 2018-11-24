package coffeeMachine.state;

import coffeeMachine.CoffeeMachine;

public interface MachineState {

  void processRequest(String action, CoffeeMachine context);

  static MachineState defaultState() {
    return new Idle();
  }
}

