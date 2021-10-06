package com.github.KluevJakov.entity;

public class DepositRequest extends Requester {
    @Override
    public boolean check(Account account) {
        return false;
    }
}
