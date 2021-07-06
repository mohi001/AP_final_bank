package Admin;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Admin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8888);
            Panel panel = new Panel(socket , scanner) ;
            String s = "" ;
            while (!s.equals("exit")){
                s = scanner.nextLine() ;
                switch (s){
                    case "admin":
                        panel.admin();
                        break;
                    case "user":
                        panel.setUser();
                        break;
                    case "users":
                        panel.getUsers();
                        break;
                    case "accounts":
                        panel.getAccounts();
                        break;
                    case "setName":
                        panel.setName();
                        break;
                    case "phone":
                        panel.setPone();
                        break;
                    case "email":
                        panel.setEmail();
                        break;
                    case "password":
                        panel.setPassword();
                        break;
                    case "balance":
                        panel.setBalance();
                        break;
                    case "transfer":
                        panel.transfer();
                        break;
                    case "close":
                        panel.close();
                        break;
                    case "up":
                        panel.signUp();
                        break;
                    case "exit":
                        panel.exit();
                        break;
                    case"new":
                        panel.newAccount();
                        break;
                }


            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
