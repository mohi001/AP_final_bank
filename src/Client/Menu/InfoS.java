package Client.Menu;

import Client.Messenger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoS extends Scene {
    private static final ScrollPane root = new ScrollPane();

    public InfoS(Stage stage, Messenger ms) throws IOException {
        super(root, 900, 700);
        root.setPadding(new Insets(50));
        Accordion accordion = new Accordion();
        String answer = ms.send("accounts");
        for (String s : answer.split("\\n"))
        {
            accordion.getPanes().add(new AccountsInfoPane(ms, s));
        }
    }
}
