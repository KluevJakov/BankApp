package com.github.KluevJakov.client;

import lombok.ToString;

@ToString
public class DistrustClient implements Client {

    private Client client;

    public DistrustClient(Client wrap) {
        this.client = wrap;
    }

    @Override
    public double paymentLimit() {
        return 1000;
    }
}
