package Server;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<User> users = new ArrayList<>() ;
        Scanner scanner = new Scanner(System.in) ;
        File file = new File("Users.txt") ;

        if (file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                users = (ArrayList<User>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            objectInputStream.close();
            fileInputStream.close() ;
        }

        ThreadBuilder threadBuilder = new ThreadBuilder(users);
        LoanSystem loanSystem = new LoanSystem(users) ;
        loanSystem.start();
        threadBuilder.start();
        scanner.nextLine();


        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream) ;
        outputStream.writeObject(users);
        System.exit(0);

    }
}
