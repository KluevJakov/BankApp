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

    public void Replenish() {

    }

    public void Withdraw() {

    }

    public void Transfer() {

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
