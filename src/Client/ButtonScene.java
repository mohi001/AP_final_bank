package Client;

import Client.Menu.Menu;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ButtonScene extends Hyperlink {
    double sizeX;
    double sizeY;
    String imageAddress;
    Scene target;
    Stage stage;

    public ButtonScene(double sizeX, double sizeY, String imageAddress, Scene target, Stage stage) throws FileNotFoundException {
        super("", new ImageView(new Image(new FileInputStream(imageAddress))));
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.imageAddress = imageAddress;
        this.target = target;
        this.stage = stage;
        setPrefWidth(sizeX);
        setPrefHeight(sizeY);
        setOnAction(e -> stage.setScene(target));
    }

    public static ButtonScene getBackButton(Messenger ms, Stage stage) throws FileNotFoundException
    {
        return new ButtonScene(50, 50, "src/Client/Resources/sign_in.png",
                new Menu(stage, ms), stage);
    }
}
