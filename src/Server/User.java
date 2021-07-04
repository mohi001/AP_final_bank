package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String identityNumber , email , password , phone , name;
    private ArrayList<Account> myAccounts ;
    private ArrayList<AliasAccount> list ;


    public User(String identityNumber, String email, String password, String phone , String name) {
        this.identityNumber = identityNumber;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name ;
        myAccounts = new ArrayList<>();
        list = new ArrayList<>();
    }

    public ArrayList<AliasAccount> getList() {
        return list;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void addMyAccount(Account account){
        myAccounts.add(account) ;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Account> getMyAccounts() {
        return myAccounts;
    }
}
