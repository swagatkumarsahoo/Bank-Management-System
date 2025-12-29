package com.swagat.bank.service;

import com.swagat.bank.exception.AccountNotFoundException;
import com.swagat.bank.model.Account;
import java.util.List;

public interface BankService {

    void createAccount(Account account);

    List<Account> getAllAccounts();

    Account getAccount(int accountNumber) throws AccountNotFoundException;

    void deposit(int accountNumber, double amount)
            throws AccountNotFoundException;

    void withdraw(int accountNumber, double amount)
            throws AccountNotFoundException;
}
