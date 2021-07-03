package Client;

import Client.Menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Menu(stage));
        stage.setTitle("|");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
