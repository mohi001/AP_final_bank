package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class LoanSystem extends Thread implements Serializable {
    private ArrayList<User> users ;
    private ArrayList<Loan> loans ;

    public LoanSystem(ArrayList<User>users){
        this.users = users ;
        setLoans();
    }

    public void setLoans() {
        for (int i = 0; i < users.size(); i++) {
            ArrayList<Account> accounts = users.get(i).getMyAccounts() ;
            for (int j = 0; j < accounts.size(); j++) {
                Loan loan = accounts.get(i).getLoan() ;
                if (!loan.isFinish()){
                    loan.start();
                }
            }
        }
    }
}
