package com.github.KluevJakov.entity;

public class DistrustClient implements Client {

    private Client client;

    public DistrustClient(Client wrap) {
        this.client = wrap;
    }


    @Override
    public String toString() {
        return "DistrustClient{" +
                "client=" + client +
                '}';
    }

    @Override
    public int paymentLimit(int limit) {
        return limit;
    }
}
