package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;

public abstract class Account {
    protected double balance;
    protected Client owner;
    protected RequestType requestType;

    public boolean replenish(double moneyAmount) {
        if (moneyAmount > 0) {
            balance += moneyAmount;
            return true;
        } else {
            return false;
        }
    }

    public abstract boolean withdraw(double moneyAmount);

    public abstract boolean transfer(Account forTransfer, double moneyAmount);

    public double getBalance() {
        return balance;
    }

    public Client getOwner() {
        return owner;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
