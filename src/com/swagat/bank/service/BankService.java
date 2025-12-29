package com.swagat.bank.service;

import com.swagat.bank.exception.AccountNotFoundException;
import com.swagat.bank.exception.InvalidTransactionException;
import com.swagat.bank.model.Account;
import java.util.List;

public interface BankService {

  void createAccount(Account account);

  List<Account> getAllAccounts();

  Account getAccount(int accountNumber)
      throws AccountNotFoundException;

  void deposit(int accNo, double amount)
      throws AccountNotFoundException, InvalidTransactionException;

  void withdraw(int accNo, double amount)
      throws AccountNotFoundException, InvalidTransactionException;

  void transfer(int fromAcc, int toAcc, double amount, int pin)
      throws AccountNotFoundException, InvalidTransactionException;
}
