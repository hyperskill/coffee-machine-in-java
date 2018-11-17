package coffeeMachine;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has: ");
        int water = scanner.nextInt();
        System.out.println ("Write how many ml of milk the coffee machine has: ");
        int milk = scanner.nextInt();
        System.out.println ("Write how many grams of coffee beans the coffee machine has: ");
        int coffee = scanner.nextInt();
        System.out.println ("Write how many cups of coffee you will need: ");
        int cups = scanner.nextInt();
int amount_w = water/200;
int amount_m = milk/50;
int amount_c = coffee-cups /15;
int[] amount = {amount_w,amount_m,amount_c};
int min = amount[0];
for (int i=0;i<3;i++)
{
    if (amount[i]<min)
    {
    min = amount[i];
    }
}

       if( min==cups) {
           System.out.println("Yes, I can make that amount of coffee");
       }
        if( min<cups) {
            System.out.println("No, I can make only ");
            System.out.print(min);
            System.out.println(" cups of coffee");
        }
        if( min>cups) {
            System.out.println("Yes, I can make that amount of coffee (and even ");
            System.out.print(min-cups);
            System.out.println(" more than that)");
        }

    }
}