package com.mydarkappfactory.hudsoncafechef;

/**
 * Created by dragonslayer on 30/10/17.
 */

public class FirebaseDish {
    private String name;
    private int quantity, price;


    public FirebaseDish(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public FirebaseDish() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
