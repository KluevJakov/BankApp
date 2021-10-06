package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;

import java.util.Date;

public class DepositAccount extends Account {

    private Date endDate;

    public DepositAccount(Client owner, double balance, Date endDate) {
        this.owner = owner;
        this.balance = balance;
        this.interest = calculateInterest(balance);
        this.commission = 0;
        this.endDate = endDate;
    }

    public double calculateInterest(double balance) {
        return balance > 1000 ? 3 : 5;
    }

    public boolean withdraw(double outgo) {
        if (balance >= outgo && endDate.before(new Date())) {
            balance -= outgo;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean transfer(Account forTransfer, double outgo) {
        if (forTransfer.getOwner().equals(this.getOwner())
                && balance >= outgo
                && endDate.before(new Date())
                && outgo <= forTransfer.getOwner().paymentLimit()) {
            balance -= outgo;
            forTransfer.replenish(outgo);
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
