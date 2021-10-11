package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class AccountFactory {

    private double interest;
    private double commission;

    public AbstractAccount getCurrent(Client owner, double balance) {
        return new CurrentAccount(owner, balance, interest);
    }

    public AbstractAccount getDeposit(Client owner, double balance, Date endDate) {
        return new DepositAccount(owner, balance, endDate);
    }

    public AbstractAccount getCredit(Client owner, double balance, int limit) {
        return new CreditAccount(owner, balance, commission, limit);
    }
}
