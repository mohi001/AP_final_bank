package Client.Menu;

import Client.InUpField;
import Client.Messenger;
import Client.ValidAble;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ReqLoanS extends Scene {
    private static final GridPane root = new GridPane();
    private final Messenger ms;

    public ReqLoanS(Stage stage, Messenger messenger, Scene back) throws IOException {
        super(root, 900, 700);
        ms = messenger;

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setHgap(30);
        root.setVgap(50);
        root.setBackground(new Background(new BackgroundFill
                (Color.INDIANRED, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));

        InUpField money = new InUpField("src/Client/Resources/money.png", "pleas inter requested money ",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        try
                        {
                            Double.parseDouble(s);
                            return true;
                        } catch (Exception e)
                        {
                            return false;
                        }
                    }
                }, "pleas inter only numbers", 0, 0, false
        );

        InUpField duration = new InUpField("src/Client/Resources/time.png", "pleas inter duration in month",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        try
                        {
                            Double.parseDouble(s);
                            return true;
                        } catch (Exception e)
                        {
                            return false;
                        }
                    }
                }, "pleas only inter number", 0, 0, false
        );
        ChoseAccount account = new ChoseAccount(ms.send("accounts"));
        account.setTranslateX(50);
        Label answer = new Label("pleas fill the fields");

        answer.setWrapText(true);
        answer.setStyle("-fx-font-size: 20");
        answer.setTranslateY(-70);

        Button req = new Button("request loan");
        req.setPrefSize(100, 50);
        req.setTranslateX(100);


        root.add(money, 0, 0);
        root.add(duration, 0, 1);
        root.add(account, 0, 2);
        root.add(req, 0, 3);
        root.add(answer, 1, 2);

        ArrayList<InUpField> fields = new ArrayList<>();
        fields.add(money);
        fields.add(duration);

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
                response = ms.sendNS("loan", money.getText(), duration.getText(),
                        account.getText());
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            if (response.equals("true"))
            {
                answer.setText("loan added \nsuccessfully");
            } else
            {
                answer.setText("failed to\n add loan");
            }
        });
        setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ESCAPE)
            {
                stage.setScene(back);
            }
        });
    }
}
