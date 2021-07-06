package Client;

import Client.Menu.Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class EmailConfS extends Scene {
    private static final VBox root = new VBox();

    public EmailConfS(Stage stage, Messenger ms, Scene back) throws IOException {
        super(root, 900, 700);
        root.setSpacing(90);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setBackground(new Background(new BackgroundFill
                (Color.YELLOW, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));

        InUpField code = new InUpField("src/Client/Resources/acc.png", "account number",
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
        Button conf = new Button("confirm code");
        conf.setMinSize(100, 50);
        conf.setTranslateY(-50);
        setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ESCAPE)
            {
                try
                {
                    ms.sendNS("exit");
                    stage.setScene(back);
                } catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        });
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
                } catch (IOException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            } else
            {
                label.setText("wrong code");
            }
        });
        root.getChildren().addAll(label, code, conf);
    }
}
