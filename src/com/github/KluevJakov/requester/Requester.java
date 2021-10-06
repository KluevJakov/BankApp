package com.github.KluevJakov.requester;

import com.github.KluevJakov.account.Account;

public abstract class Requester {
    private Requester next;

    public Requester linkWith(Requester next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(Account acc);

    protected boolean checkNext(Account acc) {
        if (next == null) {
            return false;
        }
        return next.check(acc);
    }
}
