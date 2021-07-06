package Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ErroS extends Scene {

    private static final VBox root = new VBox();

    public ErroS() {
        super(root, 900, 700);
        root.setBackground(new Background(new BackgroundFill
                (Color.DEEPSKYBLUE, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setPadding(new Insets(75));
        root.setSpacing(75);
        Label label = new Label("there is a problem :(");
        label.setWrapText(false);
        label.setStyle("-fx-font-size: 30");
        label.setPrefSize(400, 100);

        Button tryAgain = new Button("try again");
        tryAgain.setPrefSize(200, 75);
        tryAgain.setStyle("-fx-font-size: 30");
        tryAgain.setOnAction(e -> Main.main(null));
    }
}
