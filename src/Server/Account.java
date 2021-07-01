package Server;

import java.io.Serializable;

public class Account implements Serializable {
    private AccountType accountType ;
    private String password ;
    private int accountNumber ;
    private static int number = 1000 ;

    public Account(AccountType accountType, String password) {
        this.accountType = accountType;
        this.password = password;
        accountNumber = number ;
        number++ ;
    }

}
