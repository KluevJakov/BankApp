package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;
import com.github.KluevJakov.requester.Requester;

public abstract class Account {
    double balance;
    double interest;
    double commission;
    Client owner;
    private Requester requester;
    protected RequestType requestType;

    public void setRequester(Requester requester) {
        this.requester = requester;
    }

    public void accrueDeposit() {
        if (requester.check(this)) {
            replenish(balance * (interest / 100));
        }
    }

    public void accrueCommission() {
        if (requester.check(this)) {
            if (balance < 0) {
                withdraw(-1 * balance * (commission / 100));
            }
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

    public RequestType getRequestType() {
        return requestType;
    }
}
