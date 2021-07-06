package Admin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Panel {
    Socket socket ;
    DataInputStream inputStream ;
    DataOutputStream outputStream ;
    Scanner scanner ;

    public Panel(Socket socket , Scanner scanner) throws IOException {
        this.socket = socket;
        this.scanner = scanner ;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    void getUsers() throws IOException {
        outputStream.writeUTF("users");
        String s = inputStream.readUTF() ;
        System.out.println(s);
    }

    void getAccounts() throws IOException {
        outputStream.writeUTF("accounts");
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }

    void setName() throws IOException {
        outputStream.writeUTF("setName");
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }

    void setPone() throws IOException {
        outputStream.writeUTF("phone");
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }

    void setEmail() throws IOException {
        outputStream.writeUTF("email");
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }

    void setPassword() throws IOException {
        outputStream.writeUTF("password");
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());

    }

    void setBalance() throws IOException {
        outputStream.writeUTF("balance");
        String accountNumber = scanner.nextLine() ;
        outputStream.writeUTF(accountNumber);
        String balance = scanner.nextLine() ;
        outputStream.writeUTF(balance);
        System.out.println(inputStream.readUTF());
    }

    void transfer() throws IOException {
        outputStream.writeUTF("transfer");
        String sender = scanner.nextLine() ;
        String receiver = scanner.nextLine() ;
        String balance = scanner.nextLine() ;
        outputStream.writeUTF(sender);
        outputStream.writeUTF(receiver);
        outputStream.writeUTF(balance);
        System.out.println(inputStream.readUTF());
    }

    void close() throws IOException {
        outputStream.writeUTF("close");
        String accountNumber = scanner.nextLine() ;
        outputStream.writeUTF(accountNumber);
        System.out.println(inputStream.readUTF());
    }

    void admin() throws IOException {
        outputStream.writeUTF("admin");
        //password
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }

    void setUser() throws IOException {
        outputStream.writeUTF("user");
        // identity number
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }

    void signUp() throws IOException {
        outputStream.writeUTF("up");
        // name
        outputStream.writeUTF(scanner.nextLine());
        // identity
        outputStream.writeUTF(scanner.nextLine());
        //password
        outputStream.writeUTF(scanner.nextLine());
        // phone
        outputStream.writeUTF(scanner.nextLine());
        // email
        outputStream.writeUTF(scanner.nextLine());
        // code
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }

    void exit() throws IOException {
        outputStream.writeUTF("exit");
        System.out.println(inputStream.readUTF());
    }

    void newAccount() throws IOException {
        outputStream.writeUTF("new");
        // Account type
        outputStream.writeUTF(scanner.nextLine());
        // password
        outputStream.writeUTF(scanner.nextLine());
        //balance
        outputStream.writeUTF(scanner.nextLine());
        System.out.println(inputStream.readUTF());
    }
}
