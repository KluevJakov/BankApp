package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;

public interface Account {

    boolean replenish(double moneyAmount);

    boolean withdraw(double moneyAmount);

    boolean transfer(Account forTransfer, double moneyAmount);

    Client getOwner();

    double getBalance();
}
