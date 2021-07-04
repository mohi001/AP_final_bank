package Server;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int accountNumberSetter ;
    public static void main(String[] args) throws IOException {
        ArrayList<User> users = new ArrayList<>() ;
        Scanner scanner = new Scanner(System.in) ;
        File file = new File("Users.txt") ;


        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("number.txt"))) {
            accountNumberSetter = (int) inputStream.readObject();
        } catch (Exception e) {
            accountNumberSetter = 1000 ;
        }

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
        ObjectOutputStream outputStream1 = new ObjectOutputStream(new FileOutputStream("number.txt"));
        outputStream1.writeObject(accountNumberSetter);
        System.exit(0);

    }
}
