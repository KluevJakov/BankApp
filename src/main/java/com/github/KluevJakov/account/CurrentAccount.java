package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;
import lombok.ToString;

@ToString
public class CurrentAccount extends Account {

    private double interest;

    public CurrentAccount(Client owner, int balance, double interest) {
        this.owner = owner;
        this.balance = balance;
        this.interest = interest;
        this.requestType = RequestType.DEPOSIT;
    }

    @Override
    public boolean withdraw(double moneyAmount) {
        if (balance >= moneyAmount && moneyAmount > 0) {
            balance -= moneyAmount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean transfer(Account forTransfer, double moneyAmount) {
        if (forTransfer.getOwner().equals(this.getOwner())
                && balance >= moneyAmount
                && moneyAmount > 0
                && moneyAmount <= forTransfer.getOwner().paymentLimit()) {
            balance -= moneyAmount;
            forTransfer.replenish(moneyAmount);
            return true;
        } else {
            return false;
        }
    }
}
