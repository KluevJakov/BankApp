package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;

public class CurrentAccount extends Account {

    public CurrentAccount(Client owner, int balance, double interest) {
        this.owner = owner;
        this.balance = balance;
        this.interest = interest;
        this.commission = 0;
        this.requestType = RequestType.DEPOSIT;
    }

    @Override
    public boolean withdraw(double outgo) {
        if (balance >= outgo && outgo > 0) {
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
                && outgo > 0
                && outgo <= forTransfer.getOwner().paymentLimit()) {
            balance -= outgo;
            forTransfer.replenish(outgo);
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
