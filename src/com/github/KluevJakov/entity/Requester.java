package com.github.KluevJakov.entity;

public abstract class Requester {
    private Requester next;

    public Requester linkWith(Requester next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(Account account);

    protected boolean checkNext(Account account) {
        if (next == null) {
            return true;
        }
        return next.check(account);
    }
}
