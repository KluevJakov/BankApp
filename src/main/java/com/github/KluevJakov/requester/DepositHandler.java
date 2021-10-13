package com.github.KluevJakov.requester;

import com.github.KluevJakov.account.Account;

public class DepositHandler extends Handler {

    @Override
    public void processPercentage(Account account) {
        if (account.getRequestType() == RequestType.DEPOSIT) {
            System.out.println("depo request");
            return;
        }
        next.processNext(account);
    }
}
