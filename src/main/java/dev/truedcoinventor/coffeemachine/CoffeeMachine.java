package dev.truedcoinventor.coffeemachine;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CoffeeMachine {
    private static final Scanner INPUT = new Scanner(System.in);
    //RECIPES
    static Coffee cappuccino = new Coffee(200, 100, 12, 6);
    static Coffee espresso = new Coffee(250, 0, 16, 4);
    static Coffee latte = new Coffee(350, 75, 20, 7);
    // STOCK
    private static int water = 400;
    private static int milk = 540;
    private static int coffeeBean = 120;
    private static int gain = 550;
    private static int disposableCup = 9;
    // STATE
    private static int cleanliness = 10;

    public static void main(String[] args) {
        String action = "";
        Set<String> possibleActions = new HashSet<>();
        possibleActions.add("buy");
        possibleActions.add("fill");
        possibleActions.add("clean");
        possibleActions.add("take");
        possibleActions.add("remaining");
        while (!action.equals("exit")) {
            action = "";
            while (!(possibleActions.contains(action) || action.equals("exit"))) {
                System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
                action = INPUT.nextLine();
            }
            System.out.println();
            switch (action) {
                case "buy":
                    buyAction();
                    break;
                case "fill":
                    fillAction();
                    break;
                case "take":
                    takeAction();
                    break;
                case "remaining":
                    remainingAction();
                    break;
                case "clean":
                    cleanAction();
                    break;
            }
        }
    }

    private static Coffee getCoffeeByType(int type) {
        return switch (type) {
            case 1 -> espresso;
            case 2 -> latte;
            case 3 -> cappuccino;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    private static boolean checkResources(Coffee coffee) {
        if (water < coffee.getWater()) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milk < coffee.getMilk()) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (coffeeBean < coffee.getCoffeeBeans()) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        return true;
    }

    private static void processCoffeePurchase(Coffee coffee) {
        if (checkResources(coffee)) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= coffee.getWater();
            milk -= coffee.getMilk();
            coffeeBean -= coffee.getCoffeeBeans();
            gain += coffee.getPrice();
            disposableCup -= 1;
            cleanliness--;
        }
    }

    private static void buyAction() {
        String type = "0";
        if (cleanliness > 0) {
            while (Integer.parseInt(type) < 1 || Integer.parseInt(type) > 3) {
                if (type.equals("back")) {
                    return;
                }
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                type = INPUT.nextLine();
            }
            int coffeeType = Integer.parseInt(type);
            Coffee selectedCoffee = getCoffeeByType(coffeeType);
            processCoffeePurchase(selectedCoffee);
        } else {
            System.out.println("I need cleaning!");
        }
    }

    private static int getValidatedUserInputNumber(String message) {
        int number = -1;
        while (number < 0) {
            System.out.println(message);
            number = Integer.parseInt(INPUT.nextLine());
            if (number < 0) {
                System.out.println("Number should not be negative");
            }
        }
        return number;
    }

    private static void fillAction() {
        water += getValidatedUserInputNumber("Write how many ml of water you want to add:");
        milk += getValidatedUserInputNumber("Write how many ml of milk you want to add:");
        coffeeBean += getValidatedUserInputNumber("Write how many grams of coffee beans you want to add:");
        disposableCup += getValidatedUserInputNumber("Write how many disposable cups you want to add:");
        System.out.println("\n");
    }

    private static void takeAction() {
        System.out.printf("I gave you $%d\n", gain);
        gain = 0;
    }

    private static void cleanAction() {
        System.out.println("I have been cleaned!");
        cleanliness = 10;
    }

    private static void remainingAction() {
        String stock = "The coffee machine has:\n" +
                String.format("%d ml of water\n", water) +
                String.format("%d ml of milk\n", milk) +
                String.format("%d g of coffee beans\n", coffeeBean) +
                String.format("%d disposable cups\n", disposableCup) +
                String.format("$%d of money\n", gain);
        System.out.println(stock);
    }
}