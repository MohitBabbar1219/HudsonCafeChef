package com.mydarkappfactory.hudsoncafechef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dragonslayer on 4/11/17.
 */

public class Table {

    private String Customer_id;
    private boolean isVacant;
    private ArrayList<FirebaseDish> Dishes;
    private int tableNumber;

    public Table(String customer_id, boolean isVacant, ArrayList<FirebaseDish> dishes) {
        this.Customer_id = customer_id;
        this.isVacant = isVacant;
        this.Dishes = dishes;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Table(DataSnapshot dataSnapshot, int tableNumber) {
        GenericTypeIndicator<ArrayList<FirebaseDish>> t = new GenericTypeIndicator<ArrayList<FirebaseDish>>() {};
        try {
            this.Dishes = dataSnapshot.child("Dishes").getValue(t);
        } catch (Exception e) {
            this.Dishes = new ArrayList<>();
            this.Dishes.add(dataSnapshot.child("Dishes").getValue(FirebaseDish.class));
        }
        this.Customer_id = dataSnapshot.child("Customer_id").getValue(String.class);
        try {
            this.isVacant = (boolean) dataSnapshot.child("isVacant").getValue();
        } catch (Exception e) {
            System.out.println(tableNumber);
        }
        this.tableNumber = tableNumber;

    }

    public String getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(String customer_id) {
        Customer_id = customer_id;
    }


    public boolean isVacant() {
        return isVacant;
    }

    public void setVacant(boolean vacant) {
        isVacant = vacant;
    }

    public ArrayList<FirebaseDish> getDishes() {
        return Dishes;
    }

    public void setDishes(ArrayList<FirebaseDish> dishes) {
        Dishes = dishes;
    }
}
