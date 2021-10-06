package com.github.KluevJakov.requester;

import com.github.KluevJakov.account.Account;

public class DepositRequest extends Requester {
    @Override
    public boolean check(Account account) {
        System.out.println(account);
        return false;
    }
}
