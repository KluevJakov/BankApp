package com.github.KluevJakov.account;

import lombok.ToString;

@ToString
public class DistrustedAccount extends AccountDecorator {
    private double limit;

    public DistrustedAccount(double transferLimit, Account account) {
        super(account);
        this.limit = transferLimit;
    }

    @Override
    public boolean withdraw(double moneyAmount) {
        if (moneyAmount <= limit) {
            super.withdraw(moneyAmount);
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(Account forTransfer, double moneyAmount) {
        if (moneyAmount <= limit) {
            super.transfer(forTransfer, moneyAmount);
            return true;
        }
        return false;
    }

}
