package com.github.KluevJakov.entity;

public abstract class Account {
    int balance;
    double interest;
    double commission;
    Client owner;

    //logic for deposit query

    //logic for commission query

    public boolean Replenish(int income) {
        balance += income;
        return true;
    }

    public abstract boolean Withdraw(int outgo);

    public abstract boolean Transfer(Account forTransfer, int outgo);

    public int getBalance() {
        return balance;
    }

    public double getInterest() {
        return interest;
    }

    public double getCommission() {
        return commission;
    }

    public Client getOwner() {
        return owner;
    }
}
