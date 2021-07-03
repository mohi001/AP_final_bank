package Client.Menu;

import Client.ButtonScene;
import Client.Messenger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class Menu extends Scene {
    private static final VBox root = new VBox();

    public Menu(Stage stage, Messenger ms) throws FileNotFoundException {
        super(root, 900, 700);
        HBox up = new HBox();
        up.setPrefSize(900, 200);
        GridPane MainMenu = new GridPane();
        root.setBackground(new Background(new BackgroundFill
                (Color.DEEPSKYBLUE, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        addButtons(MainMenu, stage);
        root.getChildren().addAll(up, MainMenu);
    }

    private static void addButtons(GridPane p, Stage stage) throws FileNotFoundException
    {
        ButtonScene newAccount = new ButtonScene(150, 150,
                "src/Client/Resources/sign_in.png", null, stage);

        ButtonScene info = new ButtonScene(150, 150,
                "src/Client/Resources/sign_in.png", null, stage);

        ButtonScene manage = new ButtonScene(150, 150,
                "src/Client/Resources/sign_in.png", null, stage);

        ButtonScene important = new ButtonScene(150, 150,
                "src/Client/Resources/sign_in.png", null, stage);

        ButtonScene transfer = new ButtonScene(150, 150,
                "src/Client/Resources/sign_in.png", null, stage);

        ButtonScene payBill = new ButtonScene(150, 150,
                "src/Client/Resources/sign_in.png", null, stage);

        ButtonScene reqLoad = new ButtonScene(150, 150,
                "src/Client/Resources/sign_in.png", null, stage);


        ButtonScene closeAccount = new ButtonScene(150, 150,
                "src/Client/Resources/sign_in.png", null, stage);

        p.setPadding(new Insets(75));
        p.setHgap(50);
        p.setVgap(50);
        p.add(newAccount, 0, 0);
        p.add(info, 1, 0);
        p.add(manage, 2, 0);
        p.add(important, 3, 0);
        p.add(transfer, 0, 1);
        p.add(payBill, 1, 1);
        p.add(reqLoad, 2, 1);
        p.add(closeAccount, 3, 1);

    }
}
