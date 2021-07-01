package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadBuilder extends Thread{
    private ArrayList<Account>accounts ;
    private Socket socket ;
    private ServerSocket serverSocket ;
    private ExecutorService executorService ;

    public ThreadBuilder(ArrayList<Account>accounts){
        this.accounts = accounts ;
        socket = null ;
        try {
            serverSocket = new ServerSocket(8888) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService = null ;
    }

    @Override
    public void run() {
        executorService = Executors.newFixedThreadPool(100);
        while (true){
            try {
                socket = serverSocket.accept() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread thread = new Thread(new BankPanel(socket , accounts)) ;
            executorService.execute(thread);
        }

    }
}
