package Client;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InUpField extends HBox {
    String imageAddress;
    String promText;
    ValidAble validation;
    TextField textField;
    String notValid;
    Label notVL;
    double posX;
    double posY;
    boolean isPass;
    public InUpField(String imageAddress, String promText, ValidAble validation, String notValid,
                     double posX, double posY, boolean isPass) throws FileNotFoundException {
        this.imageAddress = imageAddress;
        this.promText = promText;
        this.validation = validation;
        this.notValid = notValid;
        this.posX = posX;
        this.posY = posY;
        FileInputStream in  = new FileInputStream(imageAddress);
        ImageView mv = new ImageView(new Image(in));
        mv.setFitHeight(50);
        mv.setFitWidth(50);
        getChildren().add(mv);
        if (isPass)
            textField = new PasswordField();
        else
            textField = new TextField();
        textField.setPromptText(promText);
        textField.setPrefSize(400,50);
        getChildren().add(textField);

        notVL = new Label();
        notVL.setPrefSize(400,50);
        getChildren().add(notVL);
        setTranslateX(posX);
        setTranslateY(posY);
    }
    private boolean isValid()
    {
        return validation.isValid(textField.getText());
    }
    public boolean checkValid()
    {
        if (isValid())
        {
            notVL.setText("");
            return true;
        } else
        {
            notVL.setText("   " + notValid);
            return false;
        }
    }

    public String getText()
    {
        return textField.getText();
    }
}
