package Server;

import java.io.Serializable;
import java.util.Calendar;


public class Transaction implements Serializable {
    private int accountNumber ;
    private double balance ;
    private String type ;
    private int year , month , day , hour , minute ;

    public Transaction(int accountNumber, double balance, TransactionType type) {
        this(type , balance) ;
        accountNumber = accountNumber;
    }

    public Transaction(TransactionType type, double balance) {
        this.type = type.toString() ;
        this.balance = balance;
        year = Calendar.YEAR ;
        month = Calendar.MONTH ;
        day = Calendar.DAY_OF_MONTH ;
        hour = Calendar.HOUR_OF_DAY ;
        minute = Calendar.MINUTE ;
    }

    @Override
    public String toString() {
        String s = month + "/" + day + "/" + year + " " + type + " " ;
        if (type.equals(TransactionType.RECEIVER) || type.equals(TransactionType.SENDER)){
            s += accountNumber ;
        }
        s += balance ;
        return s ;
    }
}
