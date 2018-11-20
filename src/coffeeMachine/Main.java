package coffeeMachine;

import java.util.*;

public class Main {

    public static void main(String[] args) {
      boolean cont = true;
      VendingMachine m = new VendingMachine();
      System.out.println("Hello");
      String line;
      Scanner scanner = new Scanner(System.in);

      while (cont) {
        System.out.print("Write action (buy, fill, take, exit, remaining):");
          try {
            line = scanner.nextLine().trim();
              switch (line) {
              case "buy":
                  System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - return to main menu:");
                  line = scanner.nextLine().trim();
                  switch (line) {
                    case "1":
                      m.order(Drink.ESPRESSO);
                      System.out.println("Espresso is ready.");
                      break;

                    case "2":
                      m.order(Drink.LATTE);
                      System.out.println("Latte is ready.");
                      break;

                    case "3":
                      m.order(Drink.CAPPUCHINO);
                      System.out.println("Cappuchino is ready.");
                      break;

                    case "back":
                      break;

                    default:
                      System.out.println("Unsupported drink");
                  }
                System.out.println();
                break;

              case "fill":
                  System.out.println("Write how many ml of water do you want to add:");
                  int water = Integer.parseInt(scanner.nextLine());
                  System.out.println("Write how many ml of milk do you want to add:");
                  int milk = Integer.parseInt(scanner.nextLine());
                  System.out.println("Write how many grams of coffee beans do you want to add:");
                  int beans = Integer.parseInt(scanner.nextLine());
                  System.out.println("Write how many disposable cups do you want to add:");
                  int cups = Integer.parseInt(scanner.nextLine());
                  m.refill(Refill.getInstance(water,milk,beans,cups));
                  break;

              case "take":
                  int cash = m.take();
                  System.out.println("I gave you $" + cash);
                  break;

              case "remaining":
                System.out.println(m.getState());
                break;

              case "exit":
                  cont = false;
                  break;

              default:
                  //System.out.println("Unsupported command");
            } //eof switch
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
      }//eof while
      System.out.println("Bye!");
    }//eof main()
}

class Refill implements Coffeeable {
  private Refill() {}
  private static final Refill INSTANCE = new Refill();

  public static final Refill getInstance(int water, int milk, int beans, int cups) {
    resources.put(ResourceType.WATER, water);
    resources.put(ResourceType.MILK, milk);
    resources.put(ResourceType.BEAN, beans);
    resources.put(ResourceType.CUPS, cups);
    return INSTANCE;
  }

  public Map<ResourceType, Integer> getResources(){
    return resources;
  }

  private static final Map<ResourceType, Integer> resources = new HashMap<>();
}

class Drink implements Coffeeable, Payable {
  public static final Drink ESPRESSO = new Drink(250,0,16,4);
  public static final Drink LATTE = new Drink(350,75,20,7);
  public static final Drink CAPPUCHINO = new Drink(200,100,12,6);

  public boolean isCredit(){
    return true;
  }

  public int getCashValue(){
    return cost;
  }

  private Map<ResourceType, Integer> resources = new HashMap<>();
  private int cost;

  private Drink(int water, int milk, int beans, int cost) {
    resources.put(ResourceType.WATER, water);
    resources.put(ResourceType.MILK, milk);
    resources.put(ResourceType.BEAN, beans);
    resources.put(ResourceType.CUPS, 1);
    this.cost = cost;
  }

  public Map<ResourceType, Integer> getResources(){
    return resources;
  }
}


class VendingMachine implements Coffeeable, Payable {

    public VendingMachine() {
      resources.put(ResourceType.WATER, 0);
      resources.put(ResourceType.MILK, 0);
      resources.put(ResourceType.BEAN, 0);
      resources.put(ResourceType.CUPS, 0);
    }

    public String getState() {
      StringBuilder sb = new StringBuilder();

      sb.append("The coffee machine has:");
      sb.append(System.getProperty("line.separator"));
      sb.append(resources.get(ResourceType.WATER) + " of water");
      sb.append(System.getProperty("line.separator"));
      sb.append(resources.get(ResourceType.MILK) + " of milk");
      sb.append(System.getProperty("line.separator"));
      sb.append(resources.get(ResourceType.BEAN) + " of coffee beans");
      sb.append(System.getProperty("line.separator"));
      sb.append(resources.get(ResourceType.CUPS) + " of disposable cups");
      sb.append(System.getProperty("line.separator"));
      sb.append(getCashValue() + "$ in cash");

      return sb.toString();
    }

    public int take() {
      int result = getCashValue();
      cash = 0;
      return result;
    }

    public void refill(Coffeeable refill) {
      if (refill != null) {
        for (ResourceType resourceType : refill.getResources().keySet()) {
          int avail = (resources.get(resourceType) == null) ? 0 : resources.get(resourceType);
          int additional = refill.getResources().get(resourceType);
          if (additional >= 0) {
              setResource(resourceType, avail+additional);
          } else {
            throw new IllegalArgumentException("Refill cannot be negative");
          }
        }
      } else throw new IllegalArgumentException("Cannot refill a null");
    }

    void setResource(ResourceType type, int val){
      if (val >= 0) {
        resources.put(type, val);
      } else throw new IllegalArgumentException("Resource "+ type +" value cannot be negative, got " + val);
    }

    public Map<ResourceType, Integer> getResources() {
      return resources;
    }

    public boolean isCredit() {
      return false;
    }

    public int getCashValue() {
      return cash;
    }

    public void order(Drink drink) throws MissingResourceException, IllegalArgumentException {
      if (drink != null) {
        for (ResourceType resourceType : drink.getResources().keySet()) {
          int avail = (resources.get(resourceType) == null) ? 0 : resources.get(resourceType);
          int required = (drink.getResources().get(resourceType) == null) ? 0 : drink.getResources().get(resourceType);
          if ((avail-required) < 0) throw new MissingResourceException(resourceType.toString() + " is missing in this machine. Refill required. ", "ResourceType", resourceType.toString());
          setResource(resourceType, avail-required);
        }
        if (drink.getCashValue() >= 0) {
          cash += drink.getCashValue();
        } else throw new IllegalArgumentException("Cannot vend a drink with negative cost");
      } else throw new IllegalArgumentException("Cannot vend a null drink");
    }

    private Map<ResourceType, Integer> resources = new HashMap<>();
    private int cash = 0;
}

interface Coffeeable {
  enum ResourceType {BEAN, MILK, WATER, CUPS}
  public Map<ResourceType, Integer> getResources();
}

interface Payable {
  /**
  Determines type of transaction with this item.
  If true, item costs some value (even if its 0), i.e. Credit Payable.
  This item can interact only with opposite Payable, i.e. DebitPayable,
  which should return return false and accumulate values provided with
  Credit Payables.
  **/
  public boolean isCredit();

  public int getCashValue();
}
