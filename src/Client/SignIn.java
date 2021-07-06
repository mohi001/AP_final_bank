package Client;

import Client.Menu.Menu;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SignIn extends Scene {
    private static final AnchorPane root = new AnchorPane();
    private final double height = 700;
    private final double width = 900;

    public SignIn(Stage stage, Messenger ms) throws FileNotFoundException {

        super(root, 900, 700);
        root.setBackground(new Background(new BackgroundFill
                (Color.BLUEVIOLET, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));

        InUpField codeM = new InUpField("src/Client/Resources/acc.png",
                "national code",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        try
                        {
                            Integer.parseInt(s);
                            //if (s.length() != 10)
                            return true;
                        } catch (Exception e)
                        {
                            return false;
                        }
                    }
                }, "please inter valid code",
                width / 2 - 300, height / 2 - 100, false);


        PasswordField pass = new PasswordField();
        pass.setPrefSize(400, 50);
        pass.setTranslateX(width / 2 - 200);
        pass.setTranslateY(height / 2);
        pass.setPromptText("password");


        Button button = new Button("sign in");
        button.setPrefSize(200, 50);
        button.setTranslateX(width / 2 - 100);
        button.setTranslateY(height - 100);
        button.setOnAction(e -> {
            if (codeM.checkValid())
            {
                String string = null;
                try
                {
                    string = ms.send("up " + codeM.getText() + " " + pass.getText());
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                if (string.equals("true"))
                {
                    try
                    {
                        stage.setScene(new Menu(stage, ms));
                    } catch (IOException fileNotFoundException)
                    {
                        fileNotFoundException.printStackTrace();
                    }
                } else
                {
                    codeM.setNotVL("wrong email or address");
                }
            }
        });
        root.getChildren().addAll(codeM, button, pass);
    }
}
