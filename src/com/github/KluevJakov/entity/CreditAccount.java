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

    public void Replenish() {

    }

    public void Withdraw() {

    }

    public void Transfer() {

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
