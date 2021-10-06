package com.github.KluevJakov;

import com.github.KluevJakov.account.Account;
import com.github.KluevJakov.account.AccountFactory;
import com.github.KluevJakov.client.Client;
import com.github.KluevJakov.client.DistrustClient;
import com.github.KluevJakov.client.TrustClient;
import com.github.KluevJakov.requester.*;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Client client1 = TrustClient.newBuilder("Andrew", "Javovsky")
                .setPassport(6319)
                .build();

        Client client2 = TrustClient.newBuilder("Petr", "Petrov")
                .setAddress("Address")
                .build();

        Client client3 = new DistrustClient(new TrustClient("Jakov", "Kluev"));

        AccountFactory accountFactory = new AccountFactory();

        Account myAcc1 = accountFactory.getCurrent(client1, 10000, 3.5);
        Account myAcc2 = accountFactory.getDeposit(client2, 2500, new Date());
        Account myAcc3 = accountFactory.getCredit(client3, 100, 5, 5000);

        System.out.println(myAcc1);
        System.out.println(myAcc2);
        System.out.println(myAcc3);

        Requester requester = new RequestExecutor(myAcc1);
        requester.linkWith(new DepositRequest());
        myAcc1.setRequester(requester);

        myAcc1.accrueDeposit();

        System.out.println(myAcc1);
    }
}
