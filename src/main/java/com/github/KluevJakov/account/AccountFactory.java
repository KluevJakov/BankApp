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
        if (owner.isTrusted()) {
            return new CurrentAccount(owner, balance, interest);
        }else{
            return new DistrustedAccount(transferLimit, new CurrentAccount(owner, balance, interest));
        }
    }

    public Account getDeposit(Client owner, double balance, Date endDate) {
        if (owner.isTrusted()) {
            return new DepositAccount(owner, balance, endDate);
        }else{
            return new DistrustedAccount(transferLimit, new DepositAccount(owner, balance, endDate));
        }
    }

    public Account getCredit(Client owner, double balance, int limit) {
        if (owner.isTrusted()) {
            return new CreditAccount(owner, balance, commission, limit);
        }else{
            return new DistrustedAccount(transferLimit, new CreditAccount(owner, balance, commission, limit));
        }
    }
}
