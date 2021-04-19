import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine machine = new Machine(500, 500, 500, 500, 500);
        machine.askForCommand();
        while (true) {
            String command = scanner.next();
            if (command.equals("exit")) {
                break;
            }
            machine.enterCommand(command);
        }
    }
}

class Machine {

    enum State {
        MENU, BUY, TAKE, REMAINING, FILL_WATER, FILL_MILK, FILL_COFFEE, FILL_CUPS
    }

    State state = State.MENU;

    int water, milk, coffee, money, cups;

    Machine(int water, int milk, int coffee, int money, int cups) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.money = money;
        this.cups = cups;
    }

    void askForCommand() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    void enterCommand(String command) {

        switch (state) {
            case MENU:
                switch (command) {
                    case "buy":
                        state = State.BUY;
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        break;
                    case "remaining":
                        state = State.REMAINING;
                        showRemaining();
                        returnToMenu();
                        break;
                    case "fill":
                        state = State.FILL_WATER;
                        System.out.println("Write how many ml of water do you want to add:");
                        break;
                    case "take":
                        state = State.TAKE;
                        take();
                        returnToMenu();
                        break;
                    default:
                        System.out.println("Incorrect input");
                }
                break;

            case BUY:
                buy(command);
                returnToMenu();
                break;
            case FILL_WATER:
                water += Integer.parseInt(command);
                state = State.FILL_MILK;
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            case FILL_MILK:
                milk += Integer.parseInt(command);
                state = State.FILL_COFFEE;
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case FILL_COFFEE:
                coffee += Integer.parseInt(command);
                state = State.FILL_CUPS;
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
            case FILL_CUPS:
                cups += Integer.parseInt(command);
                returnToMenu();
                break;
        }
    }

    void returnToMenu() {
        state = State.MENU;
        askForCommand();
    }

    void buy(String coffeeType) {

        if (coffeeType.equals("back")) {
            return;
        }

        int requiredWater, requiredMilk, requiredCoffee;

        switch (coffeeType) {
            case "1":
                requiredWater = 250;
                requiredCoffee = 16;
                if (badCondition(water, requiredWater)) {
                    System.out.println("Sorry, not enough water!");
                    return;
                }

                if (badCondition(coffee, requiredCoffee)) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return;
                }
                System.out.println("I have enough resources, making you a coffee!");
                water = calc(water, requiredWater);
                coffee = calc(coffee, requiredCoffee);
                money += 4;
                cups -= 1;
                break;

            case "2":
                requiredWater = 350;
                requiredMilk = 75;
                requiredCoffee = 20;
                if (badCondition(water, requiredWater)) {
                    System.out.println("Sorry, not enough water!");
                    return;
                }
                if (badCondition(milk, requiredMilk)) {
                    System.out.println("Sorry, not enough milk!");
                    return;
                }
                if (badCondition(coffee, requiredCoffee)) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return;
                }
                System.out.println("I have enough resources, making you a coffee!");
                water = calc(water, requiredWater);
                milk = calc(milk, requiredMilk);
                coffee = calc(coffee, requiredCoffee);
                money += 7;
                cups -= 1;
                break;

            case "3":
                requiredWater = 200;
                requiredMilk = 100;
                requiredCoffee = 12;
                if (badCondition(water, requiredWater)) {
                    System.out.println("Sorry, not enough water!");
                    return;
                }
                if (badCondition(milk, requiredMilk)) {
                    System.out.println("Sorry, not enough milk!");
                    return;
                }
                if (badCondition(coffee, requiredCoffee)) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return;
                }
                System.out.println("I have enough resources, making you a coffee!");
                water = calc(water, requiredWater);
                milk = calc(milk, requiredMilk);
                coffee = calc(coffee, requiredCoffee);
                money += 6;
                cups -= 1;
                break;
        }
    }

    void showRemaining() {

        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffee + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }

    void take() {

        System.out.println("I gave you $" + money);
        money = 0;
    }

    int calc(int befor, int after) {
        return befor - after;
    }

    boolean badCondition(int current, int required) {
        return current < required;
    }
}