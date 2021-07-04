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

import java.io.FileNotFoundException;

public class NewAccount extends Scene {
    private static final GridPane root = new GridPane();
    String message = "";
    String kind = "";
    private Messenger ms;

    //    public NewAccount(Stage stage, Messenger messenger) throws FileNotFoundException {
    public NewAccount(Stage stage) throws FileNotFoundException {
        super(root, 900, 700);
//        ms = messenger;

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setHgap(30);
        root.setVgap(50);

        MenuButton mB = getKindM();

        InUpField initMoney = new InUpField("src/Client/Resources/sign_in.png", "pleas inter initional money ", new ValidAble() {
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

        InUpField pass = new InUpField("src/Client/Resources/sign_in.png", "pleas inter password", new ValidAble() {
            @Override
            public boolean isValid(String s) {
                return true;
            }
        }, "", 0, 0, true
        );

        Label answer = new Label("pleas fill the fields");
        Button creat = new Button("creat new Account");
        creat.setTranslateX(100);
        mB.setTranslateX(50);
        answer.setTranslateY(-70);
        root.add(mB, 0, 0);
        root.add(initMoney, 0, 1);
        root.add(pass, 0, 2);
        root.add(creat, 0, 3);
        root.add(answer, 1, 2);


        creat.setOnAction(e -> {
            message = "new " + kind + " " + pass.getText() + " " + initMoney;
            System.out.println(message);
//            ms.send(message);

        });

    }

    private MenuButton getKindM()
    {
        MenuButton m = new MenuButton("accuont kind");
        MenuItem checking = new MenuItem("checking account ");
        checking.setOnAction(e -> {
            kind = "ch";
            m.setText("checking account ");
        });
        MenuItem saving = new MenuItem("saving account ");
        saving.setOnAction(e -> {
            kind = "s";
            m.setText("saving account ");
        });
        m.getItems().addAll(checking, saving);
        return m;
    }

}