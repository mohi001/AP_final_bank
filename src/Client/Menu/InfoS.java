package Client.Menu;

import Client.Messenger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
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

    public InfoS(Stage stage, Messenger ms, Scene back) throws IOException {
        super(root, 900, 700);
        root2.setPadding(new Insets(50));
        root2.setPrefSize(900, 800);
        root.setBackground(new Background(new BackgroundFill
                (Color.DARKGREEN, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        root2.setBackground(new Background(new BackgroundFill
                (Color.DARKGREEN, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        root.getChildren().addAll(root2);
        Accordion accordion = new Accordion();
        String answer = ms.send("accounts");
        if (answer.length() > 1)
        {
            for (String s : answer.split("\\n"))
            {
                accordion.getPanes().add(new AccountsInfoPane(ms, s));
            }
        }
        accordion.setPrefSize(800, 50);
        root2.setContent(accordion);
        setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ESCAPE)
            {
                stage.setScene(back);
            }
        });
    }
    //TODO
    //test table view
    //bug in spacing
}
