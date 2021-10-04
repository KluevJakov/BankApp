package com.github.KluevJakov.entity;

public class Client {
    private String name;
    private String surname;
    private String address;
    private int passport;

    private Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static Builder newBuilder(String name, String surname) {
        return new Client(name, surname).new Builder();
    }

    public class Builder {
        public Builder setAddress(String address) {
            Client.this.address = address;
            return this;
        }

        public Builder setPassport(int passport) {
            Client.this.passport = passport;
            return this;
        }

        public Client build() {
            return Client.this;
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public int getPassport() {
        return passport;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", passport=" + passport +
                '}';
    }
}
