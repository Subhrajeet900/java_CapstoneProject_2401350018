// Project Title: Banking Application for Account Management  
 
//Problem Statement: Design and implement a banking application that allows users to manage their bank accounts through various operations such as creating accounts, depositing money, withdrawing money, and 
//viewing account details. The application should incorporate the concepts of Java features, control structures, arrays, and strings as per the syllabus.

// CODE :-

// Project: Banking Application for Account Management
// Name: Subhrajeet
// Roll Number: 2401350018

import java.util.Scanner;


// 1. The BankAccount Class (The Data Structure)
// -------------------------------------------------------
class BankAccount {
    // Attributes
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    // Methods for Deposit and Withdraw
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn $" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
            return false;
        } else {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
    }

    public void displayAccountInfo() {
        System.out.println("\n---------------------------------");
        System.out.println(" Account Number : " + accountNumber);
        System.out.println(" Holder Name    : " + accountHolderName);
        System.out.println(" Current Balance: $" + balance);
        System.out.println("---------------------------------");
    }
}


// 2. The Main Application (Logic with Arrays)
// -------------------------------------------------------
public class BankingApplication {
    
    // Constraint: Using Arrays as per syllabus requirements
    // We assume a maximum of 100 accounts for this assignment
    private static BankAccount[] accounts = new BankAccount[100];
    private static int totalAccounts = 0; // Keeps track of how many accounts exist
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Student Bank Application ===");
        
        while (true) {
            System.out.println("\n1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Details");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdraw();
                    break;
                case 4:
                    viewAccount();
                    break;
                case 5:
                    viewAllAccounts();
                    break;
                case 6:
                    System.out.println("Thank you for using our banking app.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // --- Helper Methods ---

    // 1. Create Account
    private static void createAccount() {
        if (totalAccounts >= accounts.length) {
            System.out.println("Error: Bank storage is full (Max 100 accounts).");
            return;
        }

        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        
        // Check if account already exists
        if (findAccount(accNum) != null) {
            System.out.println("Error: Account Number " + accNum + " already exists!");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Initial Deposit: ");
        double balance = scanner.nextDouble();

        // Create object and store in Array
        accounts[totalAccounts] = new BankAccount(accNum, name, balance);
        totalAccounts++; // Increment counter
        System.out.println("Account created successfully!");
    }

    // 2. Deposit
    private static void performDeposit() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        BankAccount acc = findAccount(accNum);

        if (acc != null) {
            System.out.print("Enter Amount to Deposit: ");
            double amount = scanner.nextDouble();
            acc.deposit(amount);
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    // 3. Withdraw
    private static void performWithdraw() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        BankAccount acc = findAccount(accNum);

        if (acc != null) {
            System.out.print("Enter Amount to Withdraw: ");
            double amount = scanner.nextDouble();
            acc.withdraw(amount);
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    // 4. View Single Account
    private static void viewAccount() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        BankAccount acc = findAccount(accNum);

        if (acc != null) {
            acc.displayAccountInfo();
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    // 5. View All Accounts (Iterating through Array)
    private static void viewAllAccounts() {
        if (totalAccounts == 0) {
            System.out.println("No accounts found in the system.");
            return;
        }
        System.out.println("\n--- All Registered Accounts ---");
        // Using a Loop to iterate through the array up to totalAccounts
        for (int i = 0; i < totalAccounts; i++) {
            System.out.println((i + 1) + ". " + accounts[i].getAccountHolderName() + " (" + accounts[i].getAccountNumber() + ")");
        }
    }

    // Utility: Find account in Array by Account Number string
    private static BankAccount findAccount(String accountNumber) {
        for (int i = 0; i < totalAccounts; i++) {
            // String comparison using .equals()
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null; // Not found
    }
}
