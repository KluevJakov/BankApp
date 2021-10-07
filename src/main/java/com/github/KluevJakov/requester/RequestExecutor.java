package com.github.KluevJakov.requester;

import com.github.KluevJakov.account.Account;

public class RequestExecutor extends Requester {

    public RequestExecutor(Account acc) {

    }

    @Override
    public boolean check(Account acc) {
        return checkNext(acc);
    }
}
