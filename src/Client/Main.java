package Client;

import Client.Menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try
        {
            Socket socket = new Socket("localhost", 8888);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            Messenger messenger = new Messenger(outputStream, inputStream);
            stage.setScene(new Menu(stage, messenger));
            stage.setTitle("bank");
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("catch");
            stage.setScene(new ErroS());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
