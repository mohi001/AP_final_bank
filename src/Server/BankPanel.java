package Server;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class BankPanel implements Runnable{
    private Socket socket ;
    private DataInputStream inputStream ;
    private DataOutputStream outputStream ;
    private User user ;
    private ArrayList<User> users ;
    private ArrayList<Account>allAccounts ;


    public BankPanel(Socket socket , ArrayList<User>users){
        this.users = users ;
        this.socket = socket ;
        try {
            inputStream = new DataInputStream(socket.getInputStream()) ;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            outputStream = new DataOutputStream(socket.getOutputStream()) ;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        setAllAccounts();
        user = null ;
    }

    public void setAllAccounts() {
        allAccounts = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            ArrayList<Account>accounts = users.get(i).getMyAccounts() ;
            for (int j = 0; j < accounts.size(); j++) {
                allAccounts.add(accounts.get(j)) ;
            }
        }
    }

    @Override
    public void run() {
        String s = "" ;
        try {
            while (!s.equals("exit")) {
                s = inputStream.readUTF();
                switch (s) {
                    case "log in":
                        logIn();
                        break;
                    case "sign up":
                        signUp();
                        break;
                    case "new account":
                        newAccount();
                        break;
                    case "get accounts":
                        getMyAccounts();
                        break;
                    case "transfer":
                        transfer();
                        break;
                    case "traction":
                        getTraction();
                        break;
                    case "add list":
                        listAdd();
                        break;
                    case "get list":
                        getList();
                        break;
                    case "pay bill":
                        payBill();
                        break;
                    case "loan":
                        loan();
                        break;
                    case "set alias":
                        setAliasAccount();
                        break;
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void logIn() throws IOException {
        System.out.println("fdf");
        String identity = inputStream.readUTF() ;
        String password = inputStream.readUTF() ;
        User temp = searchUser(identity);
        if (temp == null || !temp.getPassword().equals(password))
            outputStream.writeBoolean(false);
        else {
            user = temp;
            outputStream.writeBoolean(true);
        }
    }

    private void signUp() throws IOException {
        String identity = inputStream.readUTF() ;
        String email = inputStream.readUTF() ;
        String phone = inputStream.readUTF() ;
        String password = inputStream.readUTF() ;
        User temp = searchUser(identity) ;
        if (temp == null){
            user = new User(identity , email , password , phone) ;
            users.add(user) ;
            outputStream.writeBoolean(true);
        }
        else{
            outputStream.writeBoolean(false);
        }
    }

    private void newAccount() throws IOException {
        String type = inputStream.readUTF() ;
        AccountType accountType ;

        if (type.equals(AccountType.CHECKING_ACCOUNT.toString()))
            accountType = AccountType.CHECKING_ACCOUNT ;
        else
            accountType = AccountType.SAVING_ACCOUNT ;

        String password = inputStream.readUTF() ;
        double balance = Double.parseDouble(inputStream.readUTF()) ;
        Account account = new Account(accountType , password , balance) ;
        user.addMyAccount(account);
        allAccounts.add(account) ;
        System.out.println(account);
    }

    private User searchUser(String identity){
        if (users == null)
            return null ;
        for (int i = 0; i < users.size(); i++) {
            if (identity.equals(users.get(i).getIdentityNumber()))
                return users.get(i);
        }
        return null ;
    }

    private void getMyAccounts() throws IOException {
        String s = "";
        ArrayList<Account> accounts = user.getMyAccounts();
        if (accounts == null)
            outputStream.writeUTF("");
        else {
            for (int i = 0; i < accounts.size() - 1; i++) {
                s += accounts.get(i).toString() + "\n";
            }
            if (accounts.size() > 0)
                s += accounts.get(accounts.size() - 1).toString();
            outputStream.writeUTF(s);
            System.out.println(s);
        }
    }

    private Account searchAccount(int accountNumber){
        if (allAccounts == null)
            return null ;
        for (int i = 0; i < allAccounts.size(); i++) {
            if (allAccounts.get(i).getAccountNumber() == accountNumber)
                return allAccounts.get(i);
        }
        return null ;
    }

    private void transfer() throws IOException {
        Account sender = searchAccount(Integer.parseInt(inputStream.readUTF())) ;
        Account receiver = searchAccount(Integer.parseInt(inputStream.readUTF())) ;
        String password = inputStream.readUTF() ;
        double balance = Double.parseDouble(inputStream.readUTF()) ;
        if (sender == null || receiver == null || !sender.getPassword().equals(password) || balance > sender.getBalance())
            outputStream.writeBoolean(false);
        else {
            outputStream.writeBoolean(true);
            sender.send(receiver.getAccountNumber() , balance) ;
            receiver.receive(sender.getAccountNumber() , balance);
        }
    }

    private void getTraction() throws IOException {
        Account account = searchAccount(Integer.parseInt(inputStream.readUTF())) ;
        if (account == null)
            outputStream.writeBoolean(false);
        else {
            outputStream.writeBoolean(true);
            String s = "" ;
            ArrayList<Transaction> transactions = account.getTransactions() ;
            for (int i = 0; i < transactions.size()-1; i++) {
                s += transactions.get(i).toString() + "\n" ;
            }
            if (transactions.size() > 0)
                s += transactions.get(transactions.size()-1) ;
            outputStream.writeUTF(s);
            System.out.println(s);
        }
    }

    private void listAdd() throws IOException {
        int accountNumber = Integer.parseInt(inputStream.readUTF());
        String alias = inputStream.readUTF();
        Account account = searchAccount(accountNumber) ;
        if (account == null)
            outputStream.writeBoolean(false);
        else{
            outputStream.writeBoolean(true);
            user.getList().add(new AliasAccount(accountNumber , alias)) ;
        }
    }

    private void getList() throws IOException {
        String s = "" ;
        ArrayList<AliasAccount>list = user.getList();
        for (int i = 0; i < list.size() - 1; i++) {
            s += list.get(i).getAccountNumber() + " " + list.get(i).getAlias() + "\n" ;
        }
        if (list.size() > 0)
            s += list.get(list.size()-1).getAccountNumber() + " " + list.get(list.size()-1).getAlias() ;
        outputStream.writeUTF(s);
        System.out.println(s);
    }

    private void payBill() throws IOException {
        Account sender = searchAccount(Integer.parseInt(inputStream.readUTF())) ;
        String password = inputStream.readUTF() ;
        double balance = 1 ;
        if (sender == null || !sender.getPassword().equals(password) || balance > sender.getBalance())
            outputStream.writeBoolean(false);
        else {
            outputStream.writeBoolean(true);
            sender.payBill(balance) ;
        }
    }

    private void loan() throws IOException {
        Account account = searchAccount(Integer.parseInt(inputStream.readUTF())) ;
        double balance = Double.parseDouble(inputStream.readUTF()) ;
        int numberOfMonths = Integer.parseInt(inputStream.readUTF()) ;
        if (account == null)
            outputStream.writeBoolean(false);
        else
            outputStream.writeBoolean(account.newLoan(balance , numberOfMonths));
    }

    private void setAliasAccount() throws IOException {
        Account account = searchAccount(Integer.parseInt(inputStream.readUTF()));
        String alias = inputStream.readUTF();
        account.setAlias(alias);
    }

}
