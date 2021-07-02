package Client.Menu;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class CloseAcountS extends Scene {
    private static final VBox root = new VBox();

    public CloseAcountS(Stage stage) throws FileNotFoundException {
        super(root, 900, 700);
    }
}

