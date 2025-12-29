package com.swagat.bank.app;

import com.swagat.bank.exception.AccountNotFoundException;
import com.swagat.bank.model.Account;
import com.swagat.bank.service.BankService;
import com.swagat.bank.service.BankServiceImpl;
import com.swagat.bank.exception.InvalidTransactionException;

import java.util.Scanner;

public class BankApp {

  public static void main(String[] args) {

    BankService bankService = new BankServiceImpl();
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("\n--- BANK MANAGEMENT SYSTEM ---");
      System.out.println("1. Create Account");
      System.out.println("2. View All Accounts");
      System.out.println("3. Deposit");
      System.out.println("4. Withdraw");
      System.out.println("5. Transfer Money");
      System.out.println("6. Exit");
      System.out.print("Enter choice: ");

      int choice = sc.nextInt();

      try {
        switch (choice) {

          case 1 -> {
            System.out.print("Account Number: ");
            int accNo = sc.nextInt();
            System.out.print("Account Holder Name: ");
            String name = sc.next();
            System.out.print("Initial Balance: ");
            double balance = sc.nextDouble();
            System.out.print("PIN (4 digits): ");
            int pin = sc.nextInt();

            bankService.createAccount(
                new Account(accNo, name, balance, pin));
            System.out.println("Account created successfully!");
          }

          case 2 -> bankService.getAllAccounts()
              .forEach(System.out::println);

          case 3 -> {
            System.out.print("Account Number: ");
            int accNo = sc.nextInt();
            System.out.print("Deposit Amount: ");
            double amount = sc.nextDouble();

            bankService.deposit(accNo, amount);
            System.out.println("Amount deposited!");
          }

          case 4 -> {
            System.out.print("Account Number: ");
            int accNo = sc.nextInt();
            System.out.print("Withdraw Amount: ");
            double amount = sc.nextDouble();

            bankService.withdraw(accNo, amount);
            System.out.println("Amount withdrawn!");
          }

          case 5 -> {
            System.out.print("From Account: ");
            int from = sc.nextInt();
            System.out.print("To Account: ");
            int to = sc.nextInt();
            System.out.print("Amount: ");
            double amt = sc.nextDouble();
            System.out.print("PIN: ");
            int pin = sc.nextInt();

            bankService.transfer(from, to, amt, pin);
            System.out.println("Transfer successful!");
          }

          case 6 -> {
            System.out.println("Thank you for using Bank App!");
            sc.close();
            System.exit(0);
          }

          default -> System.out.println("Invalid choice!");
        }

      } catch (AccountNotFoundException | InvalidTransactionException e) {
        System.out.println(e.getMessage());
      }

    }

  }
}
