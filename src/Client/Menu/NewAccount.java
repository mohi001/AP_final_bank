package Client.Menu;

import Client.InUpField;
import Client.Messenger;
import Client.ValidAble;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class NewAccount extends Scene {
    private static final VBox root = new VBox();
    private Messenger ms;

    public NewAccount(Stage stage, Messenger messenger) throws FileNotFoundException {
        super(root, 900, 700);
        InUpField pass = new InUpField("src/Client/Resources/sign_in.png", "oh", new ValidAble() {
            @Override
            public boolean isValid(String s) {
                return true;
            }
        }, "", 0, 0, true
        );
        ms = messenger;
    }

    private MenuButton getButton()
    {
        return null;
    }

}