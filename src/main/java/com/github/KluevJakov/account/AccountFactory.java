package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class AccountFactory {

    private double interest;
    private double commission;
    private double transferLimit;

    public Account getCurrent(Client owner, double balance) {
        Account account = new CurrentAccount(owner, balance, interest);
        if (owner.isTrusted()) {
            return account;
        } else {
            return new DistrustedAccount(transferLimit, account);
        }
    }

    public Account getDeposit(Client owner, double balance, Date endDate) {
        Account account = new DepositAccount(owner, balance, endDate);
        if (owner.isTrusted()) {
            return account;
        } else {
            return new DistrustedAccount(transferLimit, account);
        }
    }

    public Account getCredit(Client owner, double balance, int limit) {
        Account account = new CreditAccount(owner, balance, commission, limit);
        if (owner.isTrusted()) {
            return account;
        } else {
            return new DistrustedAccount(transferLimit, account);
        }
    }
}
