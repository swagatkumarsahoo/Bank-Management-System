package com.swagat.bank.service;

import com.swagat.bank.exception.AccountNotFoundException;
import com.swagat.bank.model.Account;

import java.util.ArrayList;
import java.util.List;

public class BankServiceImpl implements BankService {

    private List<Account> accounts = new ArrayList<>();

    @Override
    public void createAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public Account getAccount(int accountNumber)
            throws AccountNotFoundException {

        return accounts.stream()
                .filter(acc -> acc.getAccountNumber() == accountNumber)
                .findFirst()
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found: " + accountNumber));
    }

    @Override
    public void deposit(int accountNumber, double amount)
            throws AccountNotFoundException {

        Account acc = getAccount(accountNumber);
        acc.deposit(amount);
    }

    @Override
    public void withdraw(int accountNumber, double amount)
            throws AccountNotFoundException {

        Account acc = getAccount(accountNumber);
        acc.withdraw(amount);
    }
}
