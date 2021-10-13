package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CurrentAccount extends AbstractAccount {

    private double interest;

    public CurrentAccount(Client owner, double balance, double interest) {
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
        }
        return false;
    }

    @Override
    public boolean transfer(Account forTransfer, double moneyAmount) {
        if (forTransfer.getOwner().equals(this.getOwner())
                && balance >= moneyAmount
                && moneyAmount > 0) {
            balance -= moneyAmount;
            forTransfer.replenish(moneyAmount);
            return true;
        }
        return false;
    }
}
