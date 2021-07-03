package Server;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Loan extends Thread implements Serializable {
    private Account account ;
    private double withdraw , balance ;
    private int numberOfMonths , remainingMonths;
    private boolean finish ;

    public Loan(Account account, double balance, int numberOfMonths) {
        this.account = account;
        this.numberOfMonths = numberOfMonths;
        withdraw = (double) (balance / numberOfMonths) ;
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

    public Account getAccount() {
        return account;
    }

    public double getBalance() {
        return balance;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public int getRemainingMonths() {
        return remainingMonths;
    }
}
