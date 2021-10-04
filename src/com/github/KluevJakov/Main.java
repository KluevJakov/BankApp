package com.github.KluevJakov;

import com.github.KluevJakov.entity.*;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Client client1 = TrustClient.newBuilder("Andrew", "Javovsky")
                .setPassport(6319)
                .build();

        Client client2 = TrustClient.newBuilder("Roman", "Neviantsev")
                .setAddress("Saratov, Hrustalnaya st, 68A, 36")
                .build();

        Client client3 = TrustClient.newBuilder("Jakov", "Kluev").build();

        Client client4 = new TrustClient("Jakov","May");
        System.out.println(client4);
        client4 = new DistrustClient(new TrustClient("Jakov","May"));
        System.out.println(client4);

        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);

        AccountFactory accountFactory = new AccountFactory();

        Account myAcc1 = accountFactory.getCurrent(client3, 10000, 3.5);
        Account myAcc2 = accountFactory.getDeposit(client2, 2500, new Date());
        Account myAcc3 = accountFactory.getCredit(client3, 100, 5, 5000);

        myAcc1.replenish(1234);
        myAcc1.withdraw(3400);

        if (myAcc1.transfer(myAcc3, 1200)) {
            System.out.println("Success transaction");
        } else {
            System.out.println("Failed transaction");
        }

        System.out.println(myAcc1);
        System.out.println(myAcc2);
        System.out.println(myAcc3);
    }
}
