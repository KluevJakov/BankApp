package com.github.KluevJakov.client;

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
    public double paymentLimit() {
        return 1000;
    }
}
