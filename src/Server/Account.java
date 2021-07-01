package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    private AccountType accountType ;
    private String password ;
    private int accountNumber ;
    private static int number = 1000 ;
    private ArrayList<Transaction>transactions ;
    private double balance ;

    public Account(AccountType accountType, String password) {
        this.accountType = accountType;
        this.password = password;
        accountNumber = number ;
        number++ ;
        transactions = new ArrayList<>() ;
        balance = 0 ;
    }

    public String getAccountType() {
        return accountType.toString();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }
}
