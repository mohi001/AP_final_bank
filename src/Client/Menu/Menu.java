package Client.Menu;

import Client.ButtonScene;
import Client.Messenger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Menu extends Scene {
    private static final VBox root = new VBox();

    public Menu(Stage stage, Messenger ms) throws IOException {
        super(root, 900, 700);
        GridPane up = new GridPane();
        up.setPrefSize(900, 200);

        ButtonScene exit = new ButtonScene(150, 150,
                "src/Client/Resources/exit.png", stage);
        /*
        exit.setOnAction(e ->
        {
            try
            {
                ms.sendNS("exit");
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            Main.main(null);
        });

//TODO
         */

        Label label = new Label();
        label.setText(ms.send("name"));
        label.setStyle("-fx-font-size: 20");
        up.add(exit, 0, 0);
        up.add(label, 1, 0);

        GridPane MainMenu = new GridPane();
        root.setBackground(new Background(new BackgroundFill
                (Color.DARKGRAY, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        addButtons(MainMenu, stage, ms);
        root.getChildren().addAll(up, MainMenu);
    }

    private static void addButtons(GridPane p, Stage stage, Messenger ms) throws IOException
    {
        ButtonScene newAccount = new ButtonScene(150, 150,
                "src/Client/Resources/newacc.png", stage);
        newAccount.setOnAction(e -> {
            try
            {
                stage.setScene(new NewAccount(stage, ms));
            } catch (FileNotFoundException fileNotFoundException)
            {
                fileNotFoundException.printStackTrace();
            }
        });

        ButtonScene info = new ButtonScene(150, 150,
                "src/Client/Resources/info.png", stage);
        info.setOnAction(e -> {
            try
            {
                stage.setScene(new InfoS(stage, ms));
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });


        ButtonScene manage = new ButtonScene(150, 150,
                "src/Client/Resources/add_my_alias.png", stage);
        manage.setOnAction(e -> {
            try
            {
                stage.setScene(new ManageS(stage, ms));
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });

        ButtonScene important = new ButtonScene(150, 150,
                "src/Client/Resources/add_useful_alias.png", stage);
        important.setOnAction(e -> {
            try
            {
                stage.setScene(new UsefulS(stage, ms));
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });

        ButtonScene transfer = new ButtonScene(150, 150,
                "src/Client/Resources/transfer.png", stage);
        transfer.setOnAction(e -> {
            try
            {
                stage.setScene(new TransferS(stage, ms));
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });

        ButtonScene payBill = new ButtonScene(150, 150,
                "src/Client/Resources/bill.png", stage);
        payBill.setOnAction(e -> {
            try
            {
                stage.setScene(new PayBillS(stage, ms));
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });

        ButtonScene reqLoan = new ButtonScene(150, 150,
                "src/Client/Resources/loan.png", stage);
        reqLoan.setOnAction(e -> {
            try
            {
                stage.setScene(new ReqLoanS(stage, ms));
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });

        ButtonScene closeAccount = new ButtonScene(150, 150,
                "src/Client/Resources/delete.png", stage);
        closeAccount.setOnAction(e -> {
            try
            {
                stage.setScene(new CloseAccountS(stage, ms));
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        });

        p.setPadding(new Insets(75));
        p.setHgap(50);
        p.setVgap(50);
        p.add(newAccount, 0, 0);
        p.add(info, 1, 0);
        p.add(manage, 2, 0);
        p.add(important, 3, 0);
        p.add(transfer, 0, 1);
        p.add(payBill, 1, 1);
        p.add(reqLoan, 2, 1);
        p.add(closeAccount, 3, 1);

    }
}
