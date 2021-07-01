package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class BankPanel implements Runnable{
    private Socket socket ;
    private DataInputStream inputStream ;
    private DataOutputStream outputStream ;
    private User user ;
    private ArrayList<User> users ;

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

        user = null ;
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

                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void logIn() throws IOException {
        String identity = inputStream.readUTF() ;
        String password = inputStream.readUTF() ;
        User temp = searchUser(identity);
        if (temp == null || !temp.getPassword().equals(password))
            outputStream.writeUTF("Nok");
        else {
            user = temp;
            outputStream.writeUTF("ok");
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
            outputStream.writeUTF("ok");
        }
        else{
            outputStream.writeUTF("Nok");
        }
    }

    private void newAccount() throws IOException {
        String type = inputStream.readUTF() ;
        AccountType accountType ;

        if (type.equals("A"))
            accountType = AccountType.A ;
        else
            accountType = AccountType.B ;

        String password = inputStream.readUTF() ;
        user.addMyAccount(new Account(accountType , password));
        outputStream.writeUTF("ok");
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
}
