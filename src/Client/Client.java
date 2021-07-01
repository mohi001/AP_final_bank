package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static void logIn(DataInputStream inputStream , DataOutputStream outputStream , Scanner scanner) throws IOException {
        outputStream.writeUTF("log in");
        outputStream.writeUTF(scanner.nextLine());
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }

    private static void signUp(DataInputStream inputStream , DataOutputStream outputStream , Scanner scanner) throws IOException{
        outputStream.writeUTF("sign up");
        outputStream.writeUTF(scanner.nextLine());
        outputStream.writeUTF(scanner.nextLine());
        outputStream.writeUTF(scanner.nextLine());
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }

    private static void exit(DataOutputStream outputStream) throws IOException {
        outputStream.writeUTF("exit");
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost" , 8888);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream()) ;
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            String s ;
            do {
                s = scanner.nextLine() ;
                switch (s){
                    case "log in":
                        logIn(inputStream , outputStream , scanner);
                        break;
                    case "sign up":
                        signUp(inputStream , outputStream , scanner);
                        break;
                    case "exit":
                        exit(outputStream);
                        break;
                }
            }while (!s.equals("exit")) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

