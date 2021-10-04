package com.github.KluevJakov.entity;

public class DistrustClient extends ClientDecorator {

    public DistrustClient(Client wrap) {
        super(wrap);
    }

    @Override
    public String toString() {
        return "DistrustClient{}";
    }
}
