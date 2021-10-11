package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;

import java.util.Date;

public class AccountFactory {

    private double interest;
    private double commission;

    public AccountFactory(double interest, double commission){
        this.interest = interest;
        this.commission = commission;
    }

    public Account getCurrent(Client owner, int balance) {
        return new CurrentAccount(owner, balance, interest);
    }

    public Account getDeposit(Client owner, int balance, Date endDate) {
        return new DepositAccount(owner, balance, endDate);
    }

    public Account getCredit(Client owner, int balance, int limit) {
        return new CreditAccount(owner, balance, commission, limit);
    }
}
