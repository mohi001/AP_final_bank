package Client.Menu;

import Client.Messenger;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class AccountsInfoPane extends TitledPane {
    private String accountNum;
    boolean isSeen = false;

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
                    answer = ms.send(accountNum);
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                TableView<TransactionClient> tableView = getTable(answer);
                getChildren().add(tableView);
                isSeen = true;
            }
        });
    }

    private void setTitle(String string) {
        //set title
        String[] strings = string.split("\\s");
        accountNum = strings[0];
        String title = spacing(strings[0], 20);
        if (strings[1].equals("SAVING_ACCOUNT"))
        {
            title += spacing("saving account", 16);
        } else
        {
            title += spacing("checking account", 16);
        }
        title += spacing(strings[2], 10);
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
        for (int i = 0; i < a - sBuilder.length(); i++)
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
        table.getColumns().addAll(date, hourAndMinute, AccountTo, balance);
        return table;
    }
}
