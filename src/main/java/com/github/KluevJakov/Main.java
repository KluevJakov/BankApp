package com.github.KluevJakov;

import com.github.KluevJakov.account.Account;
import com.github.KluevJakov.account.AccountFactory;
import com.github.KluevJakov.client.Client;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("Jakov", "Kluev");
        Client client2 = Client.newBuilder("Good", "Boy")
                .setAddress("Address")
                .setPassport(9999)
                .build();

        AccountFactory accountFactory = new AccountFactory(2, 5, 100);

        Account account = accountFactory.getCurrent(client, 1000);
        Account account2 = accountFactory.getCurrent(client, 1000);

        account.transfer(account2, 200);

        System.out.println(account.getBalance());
        System.out.println(account2.getBalance() + "\n");

        account.withdraw(150);

        System.out.println(account.getBalance());
        System.out.println(account2.getBalance());
    }
}
