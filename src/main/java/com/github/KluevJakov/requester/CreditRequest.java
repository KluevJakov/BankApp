package com.github.KluevJakov.requester;

import com.github.KluevJakov.account.Account;

public class CreditRequest extends Requester {

    @Override
    public boolean check(Account acc) {
        return acc.getRequestType() == RequestType.COMMISSION;
    }
}
