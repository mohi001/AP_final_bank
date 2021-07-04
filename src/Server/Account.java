package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    private AccountType accountType ;
    private String password ;
    private int accountNumber ;
    private ArrayList<Transaction>transactions ;
    private double balance ;
    private String alias ;
    private ArrayList<AliasAccount> list ;
    private Loan loan ;

    public Account(AccountType accountType, String password , double balance) {
        this.accountType = accountType;
        this.password = password;
        accountNumber = setAccountNumber() ;
        transactions = new ArrayList<>() ;
        this.balance = balance ;
        list = new ArrayList<>() ;
        loan = null ;
        alias = null ;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean haveLoan(){
        if(loan == null || loan.isFinish())
            return false ;
        return true ;
    }

    public boolean newLoan(double balance , int numberOfMonths){
        if (!haveLoan()){
            setBalance(balance + this.balance);
            loan = new Loan(this , balance , numberOfMonths) ;
            loan.start();
            return true ;
        }
        return false ;
    }

    private synchronized int setAccountNumber(){
        Main.accountNumberSetter++ ;
        return Main.accountNumberSetter ;
    }

    public Loan getLoan() {
        return loan;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAccountType() {
        return accountType.toString();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean send(int number , double balance){
        if (getBalance() >= balance){
            this.balance -= balance ;
            transactions.add(new Transaction(number , balance , TransactionType.SENDER)) ;
            return true ;
        }
        return false ;
    }

    public void receive(int number , double balance){
        this.balance += balance ;
        transactions.add(new Transaction(number , balance , TransactionType.RECEIVER)) ;
    }

    public boolean payBill(double balance){
        if (getBalance() >= balance){
            this.balance -= balance ;
            transactions.add(new Transaction(accountNumber , balance , TransactionType.PAY_BILL)) ;
            return true ;
        }
        return false ;
    }

    public void payLoan(double balance){
        transactions.add(new Transaction(accountNumber , balance , TransactionType.LOAN)) ;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        String s = Integer.toString(accountNumber) + " " + accountType.toString() + " " + balance + " " ;
        if (alias == null)
            s += -1 ;
        else s += alias ;
        return s ;
    }
}
