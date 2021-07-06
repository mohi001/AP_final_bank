package Client.Menu;

import Client.ButtonScene;
import Client.Messenger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class Menu extends Scene {
    private static final VBox root = new VBox();

    public Menu(Stage stage, Messenger ms) throws IOException {
        super(root, 900, 700);
        HBox up = new HBox();
        up.setPrefSize(900, 200);
        GridPane MainMenu = new GridPane();
        root.setBackground(new Background(new BackgroundFill
                (Color.DEEPSKYBLUE, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        addButtons(MainMenu, stage, ms);
        root.getChildren().addAll(up, MainMenu);
    }

    private static void addButtons(GridPane p, Stage stage, Messenger ms) throws IOException
    {
        ButtonScene newAccount = new ButtonScene(150, 150,
                "src/Client/Resources/newacc.png", new NewAccount(stage, ms), stage);

        ButtonScene info = new ButtonScene(150, 150,
                "src/Client/Resources/info.png", new InfoS(stage, ms), stage);


        ButtonScene manage = new ButtonScene(150, 150,
                "src/Client/Resources/add_my_alias.png", new ManageS(stage, ms), stage);

        ButtonScene important = new ButtonScene(150, 150,
                "src/Client/Resources/add_useful_alias.png", new UsefulS(stage, ms), stage);

        ButtonScene transfer = new ButtonScene(150, 150,
                "src/Client/Resources/transfer", new TransferS(stage, ms), stage);

        ButtonScene payBill = new ButtonScene(150, 150,
                "src/Client/Resources/bill.png", new PayBillS(stage, ms), stage);

        ButtonScene reqLoan = new ButtonScene(150, 150,
                "src/Client/Resources/loan", new ReqLoanS(stage, ms), stage);

        ButtonScene closeAccount = new ButtonScene(150, 150,
                "src/Client/Resources/delete.png", new CloseAccountS(stage, ms), stage);

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
