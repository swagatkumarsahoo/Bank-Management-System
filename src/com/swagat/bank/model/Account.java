package com.swagat.bank.model;

public class Account {

    private int accountNumber;
    private String holderName;
    private double balance;
    private int pin;

    public Account(int accountNumber, String holderName, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + holderName + " | Balance: " + balance;
    }
}
