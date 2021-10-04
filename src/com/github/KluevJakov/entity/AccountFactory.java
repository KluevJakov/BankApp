package com.github.KluevJakov.entity;

public class AccountFactory {
    public static Account getAccount(AccountTypes type) {
        Account toReturn;

        switch (type) {
            case CREDIT:
                toReturn = new CreditAccount();
                break;
            case CURRENT:
                toReturn = new CurrentAccount();
                break;
            case DEPOSIT:
                toReturn = new DepositAccount();
                break;
            default:
                throw new IllegalArgumentException("Wrong Account Type");
        }

        return toReturn;
    }
}
