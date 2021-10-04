package com.github.KluevJakov.entity;

public class CurrentAccount extends Account {

    public CurrentAccount(Client owner, int balance, double interest) {
        this.owner = owner;
        this.balance = balance;
        this.interest = interest;
        this.commission = 0;
    }

    @Override
    public boolean Withdraw(int outgo) {
        if (balance >= outgo) {
            balance -= outgo;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Transfer(Account forTransfer, int outgo) {
        if (forTransfer.getOwner().equals(this.getOwner())) {
            if (balance >= outgo) {
                balance -= outgo;
            } else {
                System.out.println("Ddd");
                return false;
            }
            return true;
        } else {
            return false;
        }
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
