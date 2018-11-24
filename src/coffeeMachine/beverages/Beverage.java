package coffeeMachine.beverages;

public interface Beverage {

  String SUCCESS_MESSAGE = "I have enough resources, making you a coffee!\n\n";

  String serve();
}
