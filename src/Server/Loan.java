package Server;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Loan extends Thread implements Serializable {
    private Account account ;
    private double withdraw ;
    private int remainingMonths;
    private boolean finish ;

    public Loan(Account account, double balance, int numberOfMonths) {
        this.account = account;
        withdraw = (balance / numberOfMonths) ;
        remainingMonths = numberOfMonths ;
        finish = false ;
    }

    @Override
    public void run() {
        while (remainingMonths != 0){
            try {
                TimeUnit.DAYS.sleep(30);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            account.setBalance(account.getBalance() - withdraw);
            account.payLoan(withdraw);
            remainingMonths-- ;
        }
        finish = true ;
    }

    public boolean isFinish() {
        return finish;
    }
   }
