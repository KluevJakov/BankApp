package com.github.KluevJakov.entity;

public class CreditRequest extends Requester {

    @Override
    public boolean check(Account account) {
        return false;
    }
}
