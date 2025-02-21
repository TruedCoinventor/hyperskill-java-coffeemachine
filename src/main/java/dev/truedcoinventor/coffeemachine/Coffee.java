package dev.truedcoinventor.coffeemachine;

public class Coffee {
    private final int water;
    private final int milk;
    private final int coffeeBeans;
    private final int price;

    public Coffee(int water, int milk, int coffeeBeans, int price) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.price = price;
    }

    int getWater() {
        return water;
    }
    int getMilk() {
        return milk;
    }
    int getCoffeeBeans() {
        return coffeeBeans;
    }
    int getPrice() {
        return price;
    }
}
