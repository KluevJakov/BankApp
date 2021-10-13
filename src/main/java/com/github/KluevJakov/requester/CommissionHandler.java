package com.github.KluevJakov.requester;

import com.github.KluevJakov.account.Account;

public class CommissionHandler extends Handler {
    @Override
    public void processPercentage(Account account) {
        if (account.getRequestType() == RequestType.COMMISSION) {
            System.out.println("comm request");
            return;
        }
        next.processNext(account);
    }
}
