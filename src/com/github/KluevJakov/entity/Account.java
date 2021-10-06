package com.github.KluevJakov.entity;

public abstract class Account {
    double balance;
    double interest;
    double commission;
    Client owner;

    public void accrueDeposit() {
        replenish(balance * (interest / 100));
    }

    public void accrueCommission() {
        if (balance < 0) {
            withdraw(-1 * balance * (commission / 100));
        }
    }

    public boolean replenish(double income) {
        if (income > 0) {
            balance += income;
            return true;
        } else {
            return false;
        }
    }

    public abstract boolean withdraw(double outgo);

    public abstract boolean transfer(Account forTransfer, double outgo);

    public double getBalance() {
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
