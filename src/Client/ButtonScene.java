package Client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ButtonScene extends Button {
    double sizeX;
    double sizeY;
    String imageAddress;
    Scene target;
    Stage stage;

    public ButtonScene(double sizeX, double sizeY, String imageAddress, Scene target, Stage stage) throws FileNotFoundException {
        super("",new ImageView(new Image(new FileInputStream(imageAddress))));
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.imageAddress = imageAddress;
        this.target = target;
        this.stage = stage;
        setPrefWidth(sizeX);
        setPrefHeight(sizeY);
        setOnAction(e -> stage.setScene(target));
    }
}
