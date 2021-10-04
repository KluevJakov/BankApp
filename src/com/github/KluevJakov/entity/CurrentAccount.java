package com.github.KluevJakov.entity;

public class CurrentAccount extends Account {

    public CurrentAccount(Client owner, int balance, double interest) {
        this.owner = owner;
        this.balance = balance;
        this.interest = interest;
        this.commission = 0;
    }

    public void Replenish() {

    }

    public void Withdraw() {

    }

    public void Transfer() {

    }

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "balance=" + balance +
                ", interest=" + interest +
                ", commission=" + commission +
                ", owner=" + owner +
                '}';
    }
}
