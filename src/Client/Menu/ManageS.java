package Client.Menu;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class ManageS extends Scene {
    private static final VBox root = new VBox();

    public ManageS(Stage stage) throws FileNotFoundException {
        super(root, 900, 700);
    }
}
