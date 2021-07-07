package Client.Menu;

import Client.Messenger;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AccountsInfoPane extends TitledPane {
    private String accountNum;
    private boolean isSeen = false;

    public AccountsInfoPane(Messenger ms, String string)
    {
        setTitle(string);
        setOnMouseClicked(e ->
        {
            if (!isSeen)
            {
                String answer = null;
                try
                {
                    answer = ms.sendNS("trans", accountNum);
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                if (!answer.equals(""))
                {
//                    TableView<TransactionClient> tableView = getTable(answer);
                    VBox tableView = new VBox();
                    for (String s : answer.split("\\n"))
                    {
                        tableView.getChildren().add(new Label(s));
                    }
                    setContent(tableView);
                }
                isSeen = true;
            }
        });
        setStyle("-fx-font-size: 20");
    }

    private void setTitle(String string) {
        //set title
        String[] strings = string.split("\\s");
        accountNum = strings[0];
        String title = spacing(strings[0], 25);
        if (strings[1].equals("SAVING_ACCOUNT"))
        {
            title += spacing("saving account", 30);
        } else
        {
            title += spacing("checking account", 30);
        }
        title += spacing(strings[2], 20);
        if (strings[3].equals("-1"))
        {
            title += "       -       ";
        } else
        {
            title += spacing(strings[3], 15);
        }
        setText(title);
    }

    private String spacing(String s, int a)
    {
        StringBuilder sBuilder = new StringBuilder(s);
        int b = sBuilder.length();
        for (int i = 0; i < a - b; i++)
        {
            sBuilder.append(" ");
        }
        s = sBuilder.toString();
        return s;
    }

    private TableView<TransactionClient> getTable(String string)
    {
        TableColumn<TransactionClient, String> date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<TransactionClient, String> hourAndMinute = new TableColumn<>("hour and minute");
        hourAndMinute.setCellValueFactory(new PropertyValueFactory<>("hour"));

        TableColumn<TransactionClient, String> type = new TableColumn<>("type");
        hourAndMinute.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<TransactionClient, String> AccountTo = new TableColumn<>("destination");
        AccountTo.setCellValueFactory(new PropertyValueFactory<>("accountTo"));

        TableColumn<TransactionClient, Double> balance = new TableColumn<>("balance");
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));

        TableView<TransactionClient> table = new TableView<>();
        table.setItems(TransactionClient.getTransLise(string));
        table.getColumns().addAll(date, type, hourAndMinute, AccountTo, balance);
        return table;
    }
}
