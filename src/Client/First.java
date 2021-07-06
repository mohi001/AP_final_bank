package Client;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class First extends Scene {
    private static final AnchorPane root = new AnchorPane();
    private final double height = 700;
    private final double width = 900;

    public First(Stage stage, Messenger ms) throws FileNotFoundException {
        super(root, 900, 700);
        ButtonScene signIn = new ButtonScene(100, 100,
                "src/Client/Resources/sign_in.png", stage);
        signIn.setOnAction(e -> {
            try
            {
                stage.setScene(new SignIn(stage, ms));
            } catch (FileNotFoundException fileNotFoundException)
            {
                fileNotFoundException.printStackTrace();
            }
        });
        ButtonScene signUp = new ButtonScene(100, 100,
                "src/Client/Resources/sign_up.png", stage);
        signUp.setOnAction(e -> {
            try
            {
                stage.setScene(new SignUp(stage, ms));
            } catch (FileNotFoundException fileNotFoundException)
            {
                fileNotFoundException.printStackTrace();
            }
        });
        root.getChildren().addAll(signIn, signUp);
        signIn.setTranslateY(height / 2);
        signUp.setTranslateY(height / 2);
        signIn.setTranslateX(width / 2 - 150);
        signUp.setTranslateX(width / 2 + 50);
        root.setBackground(new Background(new BackgroundFill
                (Color.BLUEVIOLET, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
    }
}
