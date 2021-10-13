package com.github.KluevJakov.requester;

import com.github.KluevJakov.account.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentService {
    private Handler handler;

    public void process(Account account) {
        handler.processNext(account);
    }
}
