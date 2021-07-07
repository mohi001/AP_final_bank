package Client.Menu;

import Client.InUpField;
import Client.Messenger;
import Client.ValidAble;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TransferS extends Scene {
    private static final VBox root = new VBox();
    private String toCode;
    private String toName;
    MenuButton AToAlias;
    InUpField accountTo;

    public TransferS(Stage stage, Messenger ms, Scene back) throws IOException {
        super(root, 900, 700);
        root.setBackground(new Background(new BackgroundFill
                (Color.DARKGRAY, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        root.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50));
        gridPane.setHgap(30);
        gridPane.setVgap(50);
        gridPane.setAlignment(Pos.CENTER);
        Label labelFrom = new Label("from");
        labelFrom.setStyle("-fx-font-size: 30");
        Label labelTo = new Label("destination");
        labelTo.setStyle("-fx-font-size: 30");

        ChoseAccount accountFrom = new ChoseAccount(ms.send("accounts"));
        accountFrom.setPrefSize(400, 50);
        InUpField pass = InUpField.getPass();
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

        accountTo = new InUpField("src/Client/Resources/acc.png", "pleas inter destination",
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
        AToAlias = getAccountToM(ms, accountTo);
        AToAlias.setPrefSize(400, 50);
        AToAlias.setStyle("-fx-font-size: 25");
        Button trans = new Button("transfer money");
        trans.setTranslateX(50);
        trans.setPrefSize(200, 50);
        trans.setStyle("-fx-font-size: 15");

        gridPane.add(labelFrom, 0, 0);
        gridPane.add(accountFrom, 0, 1);
        gridPane.add(pass, 0, 2);
        gridPane.add(money, 0, 3);
        gridPane.add(labelTo, 1, 0);
        gridPane.add(AToAlias, 1, 1);
        gridPane.add(accountTo, 1, 2);
        gridPane.add(trans, 1, 3);
        root.getChildren().add(gridPane);

        Label answer = new Label("pleas fill the fields");
        answer.setWrapText(false);
        answer.setStyle("-fx-font-size: 30");
        answer.setPrefSize(500, 50);
        answer.setTranslateX(100);
        root.getChildren().add(answer);
        ArrayList<InUpField> fields = new ArrayList<>();
        fields.add(money);
        fields.add(accountTo);

        trans.setOnAction(e ->
        {
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
                response = ms.sendNS("transfer", money.getText(),
                        accountFrom.getAccountNum(), pass.getText(),
                        accountTo.getText());
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            if (response.equals("true"))
            {
                answer.setText("transfer \nsuccessfully");
            } else
            {
                answer.setText("failed to\n transfer");
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

    public void ref(Messenger ms) throws IOException
    {
        AToAlias = getAccountToM(ms, accountTo);
    }

    private MenuButton getAccountToM(Messenger ms, InUpField tf) throws IOException
    {
        MenuButton m = new MenuButton("account aliases");
        String answer = ms.send("UAliasL");
        Scanner sc = new Scanner(answer);
        while (sc.hasNextLine())
        {
            toName = sc.next();
            toCode = sc.next();
            MenuItem menuItem = new MenuItem(toName);
            menuItem.setOnAction(e -> {
                tf.setText(toCode);
                m.setText(toName);
            });
            m.getItems().add(menuItem);
        }
        return m;
    }
}
