package Client.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransactionClient {
    private String date;
    private String hour;
    private String type;
    private String accountTo;
    private double balance;

    public TransactionClient(String string) {
        String[] strings = string.split("\\s");
        this.date = strings[0];
        this.hour = strings[1];
        this.type = strings[2];
        this.accountTo = strings[3];
        this.balance = Double.parseDouble(strings[4]);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountTo() {
        if (accountTo.equals("-1"))
            return "-";
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static ObservableList<TransactionClient> getTransLise(String s)
    {
        ObservableList<TransactionClient> list = FXCollections.observableArrayList();
        for (String s1 : s.split("\\n"))
        {
            list.add(new TransactionClient(s1));
        }
        return list;
    }
}
