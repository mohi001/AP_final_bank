package Client;

import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ButtonScene extends Hyperlink {
    double sizeX;
    double sizeY;
    String imageAddress;
    Stage stage;

    public ButtonScene(double sizeX, double sizeY, String imageAddress, Stage stage) throws FileNotFoundException {
        super("", new ImageView(new Image(new FileInputStream(imageAddress))));
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.imageAddress = imageAddress;
        this.stage = stage;
        setPrefWidth(sizeX);
        setPrefHeight(sizeY);
    }

    public static ButtonScene getBackButton(Messenger ms, Stage stage) throws IOException
    {
        return new ButtonScene(50, 50, "src/Client/Resources/back.png",
                stage);
    }
}
