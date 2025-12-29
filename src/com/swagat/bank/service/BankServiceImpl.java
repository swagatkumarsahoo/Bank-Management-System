package com.swagat.bank.service;

import com.swagat.bank.exception.AccountNotFoundException;
import com.swagat.bank.exception.InvalidTransactionException;
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
        .orElseThrow(() -> new AccountNotFoundException(
            "Account not found: " + accountNumber));
  }

  // ✅ Deposit with validation
  @Override
  public void deposit(int accNo, double amount)
      throws AccountNotFoundException, InvalidTransactionException {

    if (amount <= 0) {
      throw new InvalidTransactionException("Invalid deposit amount");
    }

    Account acc = getAccount(accNo);
    acc.deposit(amount);
  }

  // ✅ Withdraw with validation
  @Override
  public void withdraw(int accNo, double amount)
      throws AccountNotFoundException, InvalidTransactionException {

    if (amount <= 0) {
      throw new InvalidTransactionException("Invalid withdrawal amount");
    }

    Account acc = getAccount(accNo);

    if (acc.getBalance() < amount) {
      throw new InvalidTransactionException("Insufficient balance");
    }

    acc.withdraw(amount);
  }

  // ✅ Transfer with PIN validation
  @Override
  public void transfer(int fromAcc, int toAcc, double amount, int pin)
      throws AccountNotFoundException, InvalidTransactionException {

    if (amount <= 0) {
      throw new InvalidTransactionException("Invalid transfer amount");
    }

    Account sender = getAccount(fromAcc);
    Account receiver = getAccount(toAcc);

    if (!sender.verifyPin(pin)) {
      throw new InvalidTransactionException("Invalid PIN");
    }

    if (sender.getBalance() < amount) {
      throw new InvalidTransactionException("Insufficient balance");
    }

    sender.withdraw(amount);
    receiver.deposit(amount);
  }
}
