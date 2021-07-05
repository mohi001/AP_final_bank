package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class LoanSystem extends Thread implements Serializable {
    private ArrayList<User> users ;

    public LoanSystem(ArrayList<User>users){
        this.users = users ;
        setLoans();
    }

    public void setLoans() {
        for (int i = 0; i < users.size(); i++) {
            ArrayList<Account> accounts = users.get(i).getMyAccounts() ;
            if (accounts != null) {
                for (int j = 0; j < accounts.size(); j++) {
                    Loan loan = accounts.get(j).getLoan();
                    if (loan != null && !loan.isFinish()) {
                        loan.start();
                    }
                }
            }
        }
    }
}
