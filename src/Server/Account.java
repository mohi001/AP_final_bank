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
    private String alias ;
    private ArrayList<AliasAccount> list ;
    private Loan loan ;

    public Account(AccountType accountType, String password) {
        this.accountType = accountType;
        this.password = password;
        accountNumber = setAccountNumber() ;
        transactions = new ArrayList<>() ;
        balance = 0 ;
        list = new ArrayList<>() ;
        loan = null ;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Account(AccountType accountType, String password, String alias) {
        this(accountType , password);
        this.alias = alias ;
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
        number++ ;
        return number ;
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

    public void addList(Account account , String alias){
        list.add(new AliasAccount(account.getAccountNumber() , alias)) ;
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
            transactions.add(new Transaction(number , balance , TransactionType.PAY_BILL)) ;
            return true ;
        }
        return false ;
    }

    public void payLoan(double balance){
        transactions.add(new Transaction(number , balance , TransactionType.PAY_BILL)) ;
    }
}
