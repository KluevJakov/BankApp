package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;

import java.util.Date;

public class AccountFactory {

    private int interest;
    private int commission;

    public Account getCurrent(Client owner, int balance, double interest) {
        return new CurrentAccount(owner, balance, interest);
    }

    public Account getDeposit(Client owner, int balance, Date endDate) {
        return new DepositAccount(owner, balance, endDate);
    }

    public Account getCredit(Client owner, int balance, double commission, int limit) {
        return new CreditAccount(owner, balance, commission, limit);
    }
}
