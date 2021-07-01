package Server;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private LocalDateTime date ;
    private String receiverAccountNumber ;
    private int balance ;
    private String alias ;

    public Transaction(String receiverAccountNumber, int balance) {
        this.receiverAccountNumber = receiverAccountNumber;
        this.balance = balance;
        date = LocalDateTime.now() ;
        alias = null ;
    }
    public Transaction(String receiverAccountNumber, int balance, String alias) {
        this(receiverAccountNumber , balance) ;
        this.alias = alias ;
    }

    @Override
    public String toString() {
        String s = "receiver: " ;
        if (alias == null)
            s += receiverAccountNumber ;
        else
            s += alias ;
        s += "\n" ;
        s += date.getDayOfMonth()  + " " + date.getMonth() + " " + date.getYear() + "   " + date.getHour() + ":" + date.getMinute() + "\n balance: " + balance ;
        return s ;
    }
}
