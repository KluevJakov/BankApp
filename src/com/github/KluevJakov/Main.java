package com.github.KluevJakov;

import com.github.KluevJakov.entity.Account;
import com.github.KluevJakov.entity.AccountFactory;
import com.github.KluevJakov.entity.Client;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Client client1 = Client.newBuilder("Andrew", "Javovsky")
                .setPassport(6319)
                .build();

        Client client2 = Client.newBuilder("Roman", "Neviantsev")
                .setAddress("Saratov, Hrustalnaya st, 68A, 36")
                .build();

        Client client3 = Client.newBuilder("Jakov", "Kluev").build();

        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);

        AccountFactory accountFactory = new AccountFactory();

        Account myAcc1 = accountFactory.getCurrent(client3, 10000, 3.5);
        Account myAcc2 = accountFactory.getDeposit(client2, 2500, new Date());
        Account myAcc3 = accountFactory.getCredit(client3, 100, 5, 5000);

        myAcc1.Replenish(1234);
        myAcc1.Withdraw(3400);

        if (myAcc1.Transfer(myAcc3, 1200)) {
            System.out.println("Success transaction");
        } else {
            System.out.println("Failed transaction");
        }

        System.out.println(myAcc1);
        System.out.println(myAcc2);
        System.out.println(myAcc3);
    }
}
