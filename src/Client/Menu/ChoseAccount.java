package Client.Menu;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class ChoseAccount extends MenuButton {
    private String accountNum;

    public ChoseAccount(String string)
    {
        setText("chose your account");
        setStyle("-fx-font-size: 25px");
        setPrefSize(400, 40);
        String[] accounts = string.split("\\n");
        for (String s : accounts)
        {
            MenuItem menuItem = new MenuItem();
            String[] strs = s.split("\\s");
            if (!strs[3].equals("-1"))
            {
                menuItem.setText(strs[3] + "(" + strs[0] + ")");
            } else
            {
                menuItem.setText(strs[0]);
            }
            menuItem.setOnAction(e ->
            {
                accountNum = strs[0];
                setText(menuItem.getText());
            });

            getItems().add(menuItem);
        }
    }

    public String getAccountNum() {
        return accountNum;
    }
}
