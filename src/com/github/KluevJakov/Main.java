package com.github.KluevJakov;

import com.github.KluevJakov.entity.Client;

public class Main {

    public static void main(String[] args) {
        Client client1 = Client.newBuilder("Andrew", "Slime")
                .setPassport(6319)
                .build();

        Client client2 = Client.newBuilder("Roman", "Neviantsev")
                .setAddress("Saratov, Hrustalnaya st, 68A, 36")
                .build();

        Client client3 = Client.newBuilder("Jakov", "Kluev").build();

        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);
    }
}
