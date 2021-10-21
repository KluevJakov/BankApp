package com.github.KluevJakov.account;

import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.requester.RequestType;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CreditAccount extends AbstractAccount {

    private int limit;
    private double commission;

    public CreditAccount(Client owner, double balance, double commission, int limit) {
        this.owner = owner;
        this.balance = balance;
        this.commission = commission;
        this.limit = limit;
        this.requestType = RequestType.COMMISSION;
    }

    @Override
    public boolean withdraw(double moneyAmount) {
        if (moneyAmount > 0 && balance - moneyAmount >= (limit * -1)) {
            balance -= moneyAmount;
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(Account forTransfer, double moneyAmount) {
        if (forTransfer.getOwner().equals(this.getOwner())
                && balance - moneyAmount >= (limit * -1)
                && moneyAmount > 0) {
            balance -= moneyAmount;
            forTransfer.replenish(moneyAmount);
            return true;
        }
        return false;
    }

    @Override
    public void processPercentage() {
        if (balance < 0) {
            withdraw(-1 * balance * commission / 100);
        }
    }
}
