package com.github.KluevJakov.client;

import lombok.ToString;

@ToString
public class TrustClient implements Client {
    private String name;
    private String surname;
    private String address;
    private int passport;

    public TrustClient(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public double paymentLimit() {
        return Double.MAX_VALUE;
    }

    public static Builder newBuilder(String name, String surname) {
        return new TrustClient(name, surname).new Builder();
    }

    public class Builder {
        public Builder setAddress(String address) {
            TrustClient.this.address = address;
            return this;
        }

        public Builder setPassport(int passport) {
            TrustClient.this.passport = passport;
            return this;
        }

        public TrustClient build() {
            return TrustClient.this;
        }
    }
}
