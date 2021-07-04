package Server;

import java.io.Serializable;

public class AliasAccount implements Serializable {
    private int  accountNumber ;
    private String alias ;

    public AliasAccount(int accountNumber, String alias) {
        this.accountNumber = accountNumber;
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
