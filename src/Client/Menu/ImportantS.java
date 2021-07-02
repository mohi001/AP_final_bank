package Client.Menu;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class ImportantS extends Scene {
    private static final VBox root = new VBox();

    public ImportantS(Stage stage) throws FileNotFoundException {
        super(root, 900, 700);
    }
}
