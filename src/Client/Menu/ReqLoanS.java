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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReqLoanS extends Scene {
    private static final GridPane root = new GridPane();
    String message = "";
    String number;
    String name;
    private Messenger ms;

    //    public ReqLoanS(Stage stage, Messenger messenger) throws FileNotFoundException {
    public ReqLoanS(Stage stage) throws IOException {
        super(root, 900, 700);
//        ms = messenger;

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setHgap(30);
        root.setVgap(50);

//        MenuButton mB = getKindM();

        InUpField Money = new InUpField("src/Client/Resources/sign_in.png", "pleas inter requested money ",
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

        InUpField durtion = new InUpField("src/Client/Resources/sign_in.png", "pleas inter duration in month",
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
        InUpField account = new InUpField("src/Client/Resources/sign_in.png", "pleas inter account",
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

        Label answer = new Label("pleas fill the fields\n" +
                "inter account number or chose from aliases");

        Button req = new Button("request loan");
        req.setTranslateX(100);
        MenuButton menuButton = getaccountM(ms, account);
        menuButton.setTranslateX(50);
        answer.setTranslateY(-70);
        root.add(Money, 0, 0);
        root.add(durtion, 0, 1);
        root.add(account, 0, 2);
        root.add(menuButton, 0, 3);
        root.add(req, 0, 4);
        root.add(answer, 1, 2);

        ArrayList<InUpField> fields = new ArrayList<>();
        fields.add(Money);
        fields.add(durtion);
        fields.add(account);

        req.setOnAction(e -> {
            message = " " + "loan";
            for (InUpField inUp : fields)
            {
                if (!inUp.checkValid())
                {
                    return;
                }
                message += inUp.getText() + " ";
                //TODO
            }
            System.out.println(message);
//            ms.send(message);
        });
    }

    private MenuButton getaccountM(Messenger ms, InUpField tf) throws IOException
    {
        MenuButton m = new MenuButton("account aliases");
        String answer = ms.send("MAliasL");
        Scanner sc = new Scanner(answer);
        while (sc.hasNextLine())
        {
            number = sc.next();
            name = sc.next();
            MenuItem menuItem = new MenuItem(name);
            menuItem.setOnAction(e -> {
                tf.setText(number);
                m.setText(name);
            });
            m.getItems().add(menuItem);
        }
        return m;
    }
}
