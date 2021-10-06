package com.github.KluevJakov.entity;

public class CreditAccount extends Account {

    private int limit;

    public CreditAccount(Client owner, double balance, double commission, int limit) {
        this.owner = owner;
        this.balance = balance;
        this.interest = 0;
        this.commission = commission;
        this.limit = limit;
    }

    @Override
    public boolean withdraw(double outgo) {
        if (balance - outgo >= (limit * -1)) {
            balance -= outgo;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean transfer(Account forTransfer, double outgo) {
        if (forTransfer.getOwner().equals(this.getOwner())) {
            if (balance - limit >= outgo) {
                balance -= outgo;
            } else {
                return false;
            }
            forTransfer.replenish(outgo);
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
