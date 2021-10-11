package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;
import lombok.ToString;

@ToString
public class CreditAccount extends Account {

    private int limit;
    private double commission;

    public CreditAccount(Client owner, double balance, double commission, int limit) {
        this.owner = owner;
        this.balance = balance;
        this.commission = commission;
        this.limit = limit;
        this.requestType = RequestType.COMMISSION;
    }

    @Override
    public boolean withdraw(double moneyAmount) {
        if (balance - moneyAmount >= (limit * -1)) {
            balance -= moneyAmount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean transfer(Account forTransfer, double moneyAmount) {
        if (forTransfer.getOwner().equals(this.getOwner())
                && balance - moneyAmount >= (limit * -1)
                && moneyAmount <= forTransfer.getOwner().paymentLimit()) {
            balance -= moneyAmount;
            forTransfer.replenish(moneyAmount);
            return true;
        } else {
            return false;
        }
    }
}
