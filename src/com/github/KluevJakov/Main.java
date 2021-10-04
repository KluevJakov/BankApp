package com.github.KluevJakov;

import com.github.KluevJakov.entity.Account;
import com.github.KluevJakov.entity.AccountFactory;
import com.github.KluevJakov.entity.AccountTypes;
import com.github.KluevJakov.entity.Client;

public class Main {

    public static void main(String[] args) {
        /*Client client1 = Client.newBuilder("Andrew", "Slime")
                .setPassport(6319)
                .build();

        Client client2 = Client.newBuilder("Roman", "Neviantsev")
                .setAddress("Saratov, Hrustalnaya st, 68A, 36")
                .build();

        Client client3 = Client.newBuilder("Jakov", "Kluev").build();

        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);*/

        Account myAcc1 = AccountFactory.getAccount(AccountTypes.CREDIT);
        Account myAcc2 = AccountFactory.getAccount(AccountTypes.CURRENT);
        Account myAcc3 = AccountFactory.getAccount(AccountTypes.DEPOSIT);

        System.out.println(myAcc1);
        System.out.println(myAcc2);
        System.out.println(myAcc3);
    }
}
