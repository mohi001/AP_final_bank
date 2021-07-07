package Client.Menu;

import Client.InUpField;
import Client.Messenger;
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

public class CloseAccountS extends Scene {
    private static final VBox root = new VBox();

    public CloseAccountS(Stage stage, Messenger ms, Scene back) throws IOException {
        super(root, 900, 700);
        root.setSpacing(90);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setBackground(new Background(new BackgroundFill
                (Color.ORANGERED, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        ChoseAccount choseAccount = new ChoseAccount(ms.send("accounts"));

        InUpField pass = InUpField.getPass();
        pass.setTranslateX(200);

        ChoseAccount accTo = new ChoseAccount(ms.send("accounts"));

        Label label = new Label("please chose account and enter password");
        label.setWrapText(true);
        label.setStyle("-fx-font-size: 30");
        Button close = new Button("close account");
        close.setMinSize(100, 50);
        close.setTranslateY(-50);
        close.setOnAction(e -> {
            String answer = "";
            try
            {
                answer = ms.sendNS("close", choseAccount.getAccountNum(),
                        pass.getText(), accTo.getAccountNum());
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            if (answer.equals("true"))
            {
                label.setText("account closed correctly");
            } else
            {
                label.setText("pleas enter password correctly");
            }
        });
        root.getChildren().addAll(label, choseAccount, pass, close);
        setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ESCAPE)
            {
                stage.setScene(back);
            }
        });
    }
}

