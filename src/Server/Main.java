package Server;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Account> accounts = new ArrayList<>() ;
        Scanner scanner = new Scanner(System.in) ;
        File file = new File("Accounts.txt") ;

        if (file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                accounts = (ArrayList<Account>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            objectInputStream.close();
            fileInputStream.close() ;
        }

        ThreadBuilder threadBuilder = new ThreadBuilder(accounts);
        threadBuilder.start();
        scanner.nextLine();


        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream) ;
        outputStream.writeObject(accounts);
        System.exit(0);

    }
}
