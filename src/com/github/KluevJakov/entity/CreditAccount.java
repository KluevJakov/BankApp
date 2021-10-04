package com.github.KluevJakov.entity;

public class CreditAccount extends Account {

    private int limit;

    public CreditAccount(Client owner, int balance, double commission, int limit) {
        this.owner = owner;
        this.balance = balance;
        this.interest = 0;
        this.commission = commission;
        this.limit = limit;
    }

    @Override
    public boolean Withdraw(int outgo) {
        if (balance - limit >= outgo) {
            balance -= outgo;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Transfer(Account forTransfer, int outgo) {
        if (forTransfer.getOwner().equals(this.getOwner())) {
            if (balance - limit >= outgo) {
                balance -= outgo;
            } else {
                return false;
            }
            forTransfer.Replenish(outgo);
            return true;
        } else {
            return false;
        }
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "CreditAccount{" +
                "balance=" + balance +
                ", interest=" + interest +
                ", commission=" + commission +
                ", owner=" + owner +
                ", limit=" + limit +
                '}';
    }
}
