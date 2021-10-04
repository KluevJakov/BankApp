package com.github.KluevJakov.entity;

import java.util.Date;

public class DepositAccount extends Account {

    private Date endDate;

    public DepositAccount(Client owner, int balance, Date endDate) {
        this.owner = owner;
        this.balance = balance;
        this.interest = 0; //logic interest calculation
        this.commission = 0;
        this.endDate = endDate;
    }

    public boolean Withdraw(int outgo) {
        if (balance >= outgo && endDate.before(new Date())) {
            balance -= outgo;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Transfer(Account forTransfer, int outgo) {
        if (forTransfer.getOwner().equals(this.getOwner())) {
            if (balance >= outgo && endDate.before(new Date())) {
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

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "DepositAccount{" +
                "balance=" + balance +
                ", interest=" + interest +
                ", commission=" + commission +
                ", owner=" + owner +
                ", endDate=" + endDate +
                '}';
    }
}
