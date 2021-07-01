package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class BankPanel implements Runnable{
    private Socket socket ;
    private DataInputStream inputStream ;
    private DataOutputStream outputStream ;
    private Account account ;
    private ArrayList<Account> accounts ;
    public BankPanel(Socket socket , ArrayList<Account>accounts){
        this.accounts = accounts ;
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
        account = null ;
    }
    @Override
    public void run() {
        String s = "" ;
        while (!s.equals("exit")){
            try {
                s = inputStream.readUTF();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            switch (s){
                case "log in":
                    try {
                        System.out.println("ss");
                        logIn();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "sign up":
                    try {
                        signUp();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }

    private void logIn() throws IOException {
        String identity = inputStream.readUTF() ;
        String password = inputStream.readUTF() ;
        Account temp = searchAccount(identity);
        if (temp == null || !temp.getPassword().equals(password))
            outputStream.writeUTF("Nok");
        else {
            account = temp;
            outputStream.writeUTF("ok");
        }
    }

    private void signUp() throws IOException {
        String identity = inputStream.readUTF() ;
        String email = inputStream.readUTF() ;
        String phone = inputStream.readUTF() ;
        String password = inputStream.readUTF() ;
        Account temp = searchAccount(identity) ;
        if (temp == null){
            account = new Account(identity , email , password , phone) ;
            accounts.add(account) ;
            outputStream.writeUTF("ok");
        }
        else{
            outputStream.writeUTF("Nok");
        }
    }

    private Account searchAccount(String identity){
        if (accounts == null)
            return null ;
        for (int i = 0; i < accounts.size(); i++) {
            if (identity.equals(accounts.get(i).getIdentityNumber()))
                return accounts.get(i);
        }
        return null ;
    }
}
