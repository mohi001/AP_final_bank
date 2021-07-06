package Client;

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
            stage.setScene(new EmailConfS(stage, messenger));
            stage.setTitle("|");
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
            stage.setScene(new ErroS());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
