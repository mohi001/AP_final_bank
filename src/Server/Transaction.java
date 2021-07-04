package Server;

import java.io.Serializable;
import java.time.LocalDateTime;



public class Transaction implements Serializable {
    private int accountNumber ;
    private double balance ;
    private String type ;
    private int year , month , day , hour , minute ;

    public Transaction(int accountNumber, double balance, TransactionType type) {
        this(type , balance) ;
        this.accountNumber = accountNumber;
    }

    public Transaction(TransactionType type, double balance) {
        this.type = type.toString() ;
        this.balance = balance;
        LocalDateTime localDateTime = LocalDateTime.now() ;
        year = localDateTime.getYear() ;
        month = localDateTime.getMonthValue() ;
        day = localDateTime.getDayOfMonth() ;
        hour = localDateTime.getHour() ;
        minute = localDateTime.getMinute() ;
    }

    @Override
    public String toString() {
        String s = month + "/" + day + "/" + year + " " + hour + ":" + minute + " " + type + " " ;
        if (type.equals(TransactionType.RECEIVER.toString()) || type.equals(TransactionType.SENDER.toString())){
            s += accountNumber ;
        }
        else {
            s += -1 ;
        }
        s += " " + balance ;
        return s ;
    }
}
