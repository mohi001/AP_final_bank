package Client;

import Client.Menu.NewAccount;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Socket socket = new Socket("localhost", 8888);
//        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//        Messenger messenger = new Messenger(outputStream, inputStream);
        stage.setScene(new NewAccount(stage));
        stage.setTitle("|");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
