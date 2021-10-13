package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
public abstract class AbstractAccount implements Account {
    protected double balance;
    protected Client owner;
    protected RequestType requestType;

    public boolean replenish(double moneyAmount) {
        if (moneyAmount > 0) {
            balance += moneyAmount;
            return true;
        }
        return false;
    }

    public abstract boolean withdraw(double moneyAmount);

    public abstract boolean transfer(Account forTransfer, double moneyAmount);

    public Client getOwner() {
        return owner;
    }
}
