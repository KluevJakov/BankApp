package com.github.KluevJakov;

import com.github.KluevJakov.entity.*;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Client client1 = TrustClient.newBuilder("Andrew", "Javovsky")
                .setPassport(6319)
                .build();

        Client client2 = TrustClient.newBuilder("Petr", "Petrov")
                .setAddress("Address")
                .build();

        Client client3 = new DistrustClient(TrustClient.newBuilder("Jakov", "Kluev").build());

        AccountFactory accountFactory = new AccountFactory();

        Account myAcc1 = accountFactory.getCurrent(client1, 10000, 3.5);
        Account myAcc2 = accountFactory.getDeposit(client2, 2500, new Date());
        Account myAcc3 = accountFactory.getCredit(client3, 100, 5, 5000);

        if (myAcc1.replenish(1234)) {
            System.out.println("Success transaction");
        } else {
            System.out.println("Failed transaction");
        }

        if (myAcc3.withdraw(3400)) {
            System.out.println("Success transaction");
        } else {
            System.out.println("Failed transaction");
        }

        if (myAcc1.transfer(myAcc3, 1200)) {
            System.out.println("Success transaction");
        } else {
            System.out.println("Failed transaction");
        }

        System.out.println(myAcc1);
        System.out.println(myAcc2);
        System.out.println(myAcc3);

        myAcc1.accrueDeposit();
        myAcc2.accrueDeposit();
        myAcc3.accrueCommission();

        System.out.println(myAcc1);
        System.out.println(myAcc2);
        System.out.println(myAcc3);

        Requester requester = new DepositRequest();
    }
}
