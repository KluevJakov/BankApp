package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;

public class AccountDecorator implements Account {
    private Account wrappee;

    AccountDecorator(Account account) {
        this.wrappee = account;
    }

    @Override
    public boolean replenish(double moneyAmount) {
        return wrappee.replenish(moneyAmount);
    }

    @Override
    public boolean withdraw(double moneyAmount) {
        return wrappee.withdraw(moneyAmount);
    }

    @Override
    public boolean transfer(Account forTransfer, double moneyAmount) {
        return wrappee.transfer(forTransfer, moneyAmount);
    }

    @Override
    public Client getOwner() {
        return wrappee.getOwner();
    }

    @Override
    public double getBalance() {
        return wrappee.getBalance();
    }

    @Override
    public RequestType getRequestType() {
        return wrappee.getRequestType();
    }
}
