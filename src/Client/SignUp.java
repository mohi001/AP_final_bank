package Client;


import Client.Menu.Menu;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends Scene {
    private static final AnchorPane root = new AnchorPane();
    private final double height = 700;
    private final double width = 900;

    public SignUp(Stage stage, Messenger ms) throws FileNotFoundException {

        super(root, 900, 700);
        root.setBackground(new Background(new BackgroundFill
                (Color.BLUEVIOLET, new CornerRadii(1),
                        new Insets(0.0, 0.0, 0.0, 0.0))));

        InUpField name = new InUpField("src/Client/Resources/sign_in.png",
                "name",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        if (s.split("\\s").length == 2)
                        {
                            return true;
                        }
                        return false;
                    }
                }, "please inter valid Name",
                width / 2 - 250, height / 2 - 200,false);

        InUpField codeM = new InUpField("src/Client/Resources/sign_in.png",
                "national code",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        try
                        {
                            Integer.parseInt(s);
                            if (s.length() == 10)
                                return true;
                        } catch (Exception e)
                        {
                            return false;
                        }
                        return false;
                    }
                }, "please inter valid code(with 0)",
                width / 2 - 250, height / 2 - 130, false);
        InUpField pass = new InUpField("src/Client/Resources/sign_in.png",
                "password",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        return true;
                    }
                }, "",
                width / 2 - 250, height / 2 - 60, true);

        InUpField phoneNumber = new InUpField("src/Client/Resources/sign_in.png",
                "phone number",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        try
                        {
                            Integer.parseInt(s);
                            if (s.length() == 11)
                                return true;
                        }catch (Exception e)
                        {
                            return false;
                        }
                        return false;
                    }
                }, "please inter valid phone number",
                width / 2 - 250, height / 2 + 10,false);
        InUpField email = new InUpField("src/Client/Resources/sign_in.png",
                "email",
                new ValidAble() {
                    @Override
                    public boolean isValid(String s) {
                        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
                        Matcher match = pattern.matcher(s);
                        return match.matches();
                    }
                }, "please inter valid phone email",
                width / 2 - 250, height / 2 + 80, false);

        ArrayList<InUpField> fields = new ArrayList<InUpField>();
        fields.add(name);
        fields.add(codeM);
        fields.add(pass);
        fields.add(phoneNumber);
        fields.add(email);

        Button button = new Button("sign up");
        button.setPrefSize(200, 50);
        button.setTranslateX(width / 2 - 100);
        button.setTranslateY(height - 100);
        button.setOnAction(e ->
        {
            String string = "up";
            for (InUpField f : fields)
            {
                if (!f.checkValid())
                {
                    //TODO
                    return;
                }

                string += f.getText() + " ";
            }
            String result = ms.send(string);
            if (result.equals("true"))
            {
                try
                {
                    stage.setScene(new Menu(stage, ms));
                } catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            } else
            {
                //TODO
            }
        });
        root.getChildren().addAll(button);
        root.getChildren().addAll(fields);
    }
}
