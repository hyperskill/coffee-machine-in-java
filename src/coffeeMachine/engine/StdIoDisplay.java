package coffeeMachine.engine;

public class StdIoDisplay implements Display {

  @Override
  public void displayMessage(String message) {
    System.out.printf(message);
  }
}
