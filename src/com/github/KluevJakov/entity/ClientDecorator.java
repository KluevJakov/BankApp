package com.github.KluevJakov.entity;

public class ClientDecorator implements Client {
    private Client wrap;

    public ClientDecorator(Client wrap) {
        this.wrap = wrap;
    }
}
