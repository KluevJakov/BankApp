package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;

public interface Account {

    boolean replenish(double moneyAmount);

    boolean withdraw(double moneyAmount);

    boolean transfer(Account forTransfer, double moneyAmount);

    Client getOwner();

    double getBalance();

    RequestType getRequestType();
}
