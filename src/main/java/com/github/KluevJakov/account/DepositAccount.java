package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;
import lombok.ToString;

import java.util.Date;

@ToString
public class DepositAccount extends Account {

    private Date endDate;
    private double interest;

    public DepositAccount(Client owner, double balance, Date endDate) {
        this.owner = owner;
        this.balance = balance;
        this.interest = calculateInterest(balance);
        this.endDate = endDate;
        this.requestType = RequestType.DEPOSIT;
    }

    private double calculateInterest(double balance) {
        return balance > 1000 ? 3 : 5;
    }

    public boolean withdraw(double moneyAmount) {
        if (balance >= moneyAmount && endDate.after(new Date())) {
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
                && endDate.after(new Date())
                && moneyAmount <= forTransfer.getOwner().paymentLimit()) {
            balance -= moneyAmount;
            forTransfer.replenish(moneyAmount);
            return true;
        } else {
            return false;
        }
    }
}
