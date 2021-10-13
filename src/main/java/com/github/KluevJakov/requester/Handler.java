package com.github.KluevJakov.requester;

import com.github.KluevJakov.account.Account;
import lombok.ToString;

@ToString
public abstract class Handler {
    protected Handler next;

    public Handler linkWith(Handler next) {
        this.next = next;
        return next;
    }

    public abstract void processPercentage(Account account);

    public void processNext(Account account) {
        if (next == null) {
            processPercentage(account);
            return;
        }
        next.processPercentage(account);
    }
}
