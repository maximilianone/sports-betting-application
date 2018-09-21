package com.epam.training.sportsbetting.domain.user;

import com.epam.training.sportsbetting.domain.wager.Currency;

import java.time.LocalDate;

public class Player extends User {
    private String accountNumber;
    private double balance;
    private Currency currency;

    public Player() {

    }

    public Player(String name, LocalDate dateOfBirth, String email, String password, boolean enabled,
                  String accountNumber, double balance, Currency currency) {
        super(name, dateOfBirth, email, password, Access.PLAYER, enabled);
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public void setAccess(Access access) {

    }
}
