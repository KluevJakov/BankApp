package com.github.KluevJakov.entity;

public abstract class Account {
    int balance;
    int interest;
    int commission;
    Client owner;

    abstract void Replenish();

    abstract void Withdraw();

    abstract void Transfer();
}
