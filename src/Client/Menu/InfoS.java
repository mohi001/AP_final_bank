package Client.Menu;

import Client.ButtonScene;
import Client.Messenger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoS extends Scene {
    private static final ScrollPane root2 = new ScrollPane();
    private static final VBox root = new VBox();

    public InfoS(Stage stage, Messenger ms) throws IOException {
        super(root, 900, 700);
        root2.setPadding(new Insets(50));
        root2.setPrefSize(900, 800);
        root.setBackground(new Background(new BackgroundFill
                (Color.DARKGREEN, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        root2.setBackground(new Background(new BackgroundFill
                (Color.DARKGREEN, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        ButtonScene back = ButtonScene.getBackButton(ms, stage);
        root.getChildren().addAll(back, root2);
        Accordion accordion = new Accordion();
        String answer = ms.send("accounts");
        for (String s : answer.split("\\n"))
        {
            accordion.getPanes().add(new AccountsInfoPane(ms, s));
        }
        accordion.setPrefSize(800, 50);
        root2.setContent(accordion);
    }
    //TODO
    //test table view
    //bug in spacing
}
