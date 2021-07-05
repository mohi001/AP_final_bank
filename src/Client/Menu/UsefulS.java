package Client.Menu;

import Client.InUpField;
import Client.Messenger;
import Client.ValidAble;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class UsefulS extends Scene {
    private static final GridPane root = new GridPane();
    String message = "";
    String number;
    String name;
    private Messenger ms;

    //    public UsefulS(Stage stage, Messenger messenger) throws FileNotFoundException {
    public UsefulS(Stage stage) throws FileNotFoundException {
        super(root, 900, 700);
//        ms = messenger;

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setHgap(30);
        root.setVgap(50);

//        MenuButton mB = getKindM();

        InUpField code = new InUpField("src/Client/Resources/sign_in.png", "pleas inter requested money ",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        try
                        {
                            Integer.parseInt(s);
                            return true;
                        } catch (Exception e)
                        {
                            return false;
                        }
                    }
                }, "pleas inter only numbers", 0, 0, false
        );

        InUpField name = new InUpField("src/Client/Resources/sign_in.png", "pleas inter duration in month",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        return s.split("\\s").length == 1;
                    }
                }, "don't inter space", 0, 0, false

        );

        Label answer = new Label("pleas fill the fields\n" +
                "inter account number or chose from aliases");
        answer.setWrapText(true);
        Button req = new Button("request loan");
        req.setTranslateX(100);
        answer.setTranslateY(-70);
        root.add(code, 0, 1);
        root.add(name, 0, 2);
        root.add(req, 0, 4);
        root.add(answer, 1, 2);

        ArrayList<InUpField> fields = new ArrayList<>();
        fields.add(code);
        fields.add(name);

        req.setOnAction(e -> {
            message = "bill" + " ";
            for (InUpField inUp : fields)
            {
                if (!inUp.checkValid())
                {
                    return;
                }
                message += inUp.getText() + " ";
                //TODO
            }
            System.out.println(message);
//            ms.send(message);
        });
    }

}
