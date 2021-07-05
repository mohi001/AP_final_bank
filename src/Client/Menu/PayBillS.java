package Client.Menu;

import Client.ButtonScene;
import Client.InUpField;
import Client.Messenger;
import Client.ValidAble;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PayBillS extends Scene {
    private static final GridPane root = new GridPane();
    String message = "";
    String number;
    String name;

    public PayBillS(Stage stage, Messenger ms) throws IOException {
        super(root, 900, 700);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setHgap(30);
        root.setVgap(50);
        root.setBackground(new Background(new BackgroundFill
                (Color.DARKGRAY, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        root.setAlignment(Pos.CENTER);

        InUpField code = new InUpField("src/Client/Resources/sign_in.png", "pleas inter requested money ",
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

        InUpField serial = new InUpField("src/Client/Resources/sign_in.png", "pleas inter duration in month",
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
                }, "pleas only inter number", 0, 0, false
        );

        ChoseAccount account = new ChoseAccount(ms.send("accounts"));
        InUpField pass = InUpField.getPass();
        Label answer = new Label("pleas fill the fields");
        answer.setWrapText(true);
        answer.setTranslateX(-30);
        answer.setStyle("-fx-font-size: 20");
        Button req = new Button("pay bill");
        req.setTranslateX(100);
        req.setPrefSize(100, 50);
        account.setTranslateX(50);
        answer.setTranslateY(-70);
        root.add(serial, 0, 0);
        root.add(code, 0, 1);
        root.add(account, 0, 2);
        root.add(pass, 0, 3);
        root.add(req, 0, 4);
        root.add(answer, 1, 2);
        ButtonScene back = ButtonScene.getBackButton(ms, stage);
        root.add(back, 1, 3);
        back.setTranslateX(-700);
        back.setTranslateY(-400);

        ArrayList<InUpField> fields = new ArrayList<>();
        fields.add(serial);
        fields.add(code);

        req.setOnAction(e -> {
            for (InUpField inUp : fields)
            {
                if (!inUp.checkValid())
                {
                    return;
                }
            }
            String response = "";
            try
            {
                response = ms.sendNS("bill", account.getAccountNum(), pass.getText());
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            if (response.equals("true"))
            {
                answer.setText("bill payed \nsuccessfully");
            } else
            {
                answer.setText("failed to\n pay bill");
            }
        });
    }


}
