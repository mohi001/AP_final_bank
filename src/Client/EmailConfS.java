package Client;

import Client.Menu.Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EmailConfS extends Scene {
    private static final VBox root = new VBox();

    public EmailConfS(Stage stage, Messenger ms) throws IOException {
        super(root, 900, 700);
        root.setSpacing(90);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setBackground(new Background(new BackgroundFill
                (Color.YELLOW, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));

        InUpField code = new InUpField("src/Client/Resources/sign_in.png", "account number",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        try
                        {
                            Integer.parseInt(s);
                            return true;
                        } catch (Exception e)
                        {
                            return false;
                        }
                    }
                }, "pleas inter only numbers", 0, 0, false
        );
        code.setTranslateX(200);

        Label label = new Label("please chek your email \nand inter code");
        label.setWrapText(false);
        label.setStyle("-fx-font-size: 30");
        label.setPrefSize(400, 100);
        Button conf = new Button("add alias");
        conf.setMinSize(100, 50);
        conf.setTranslateY(-50);
        ButtonScene back = ButtonScene.getBackButton(ms, stage);
        back.setOnAction(e ->
        {
            try
            {
                ms.sendNS("exit");
                stage.setScene(new SignUp(stage, ms));
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });
        back.setTranslateX(-400);
        back.setTranslateY(-48);
        conf.setOnAction(e -> {
            String answer = "";
            try
            {
                answer = ms.sendNS(code.getText());
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            if (answer.equals("true"))
            {
                try
                {
                    stage.setScene(new Menu(stage, ms));
                } catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            } else
            {
                label.setText("wrong code");
            }
        });
        root.getChildren().addAll(back, label, code, conf);
    }
}
