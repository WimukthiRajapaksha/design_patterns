/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author wimukthirajapaksha
 */

class BankAccount {
    private int balance=200;
    private int overdraftLimit = -500;
    
    public void deposit(int amount) {
        balance+=amount;
        System.out.println("Deposit "+amount+" balance "+balance);
    }
    
    public boolean withdraw(int amount) {
        if(balance-amount>=overdraftLimit) {
            balance-=amount;
            System.out.println("Withdrew "+amount+" balance "+balance);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount {" + "balance=" + balance + ", overdraftLimit=" + overdraftLimit + '}';
    }
}

interface iCommand {
    void call();
    void undo();
}

class BankAccountCommand implements iCommand {
    private BankAccount account;
    private boolean succeeded=true;
    
    public enum Action {
        DEPOSIT, WITHDRAW
    }
    
    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void call() {
        switch (action) {
            case DEPOSIT:
                account.deposit(amount);
                break;
            case WITHDRAW:
                succeeded = account.withdraw(amount);
                break;
        }
    }

    @Override
    public void undo() {
        if(!succeeded) return;
        switch(action) {
            case DEPOSIT:
                account.withdraw(amount);
                break;
            case WITHDRAW:
                account.deposit(amount);
                break;
        }
    }
}

public class Command {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BankAccount bankAccount=new BankAccount();
        System.out.println(bankAccount);
        List<BankAccountCommand> list=Arrays.asList(
            new BankAccountCommand(bankAccount, BankAccountCommand.Action.DEPOSIT, 100),
            new BankAccountCommand(bankAccount, BankAccountCommand.Action.WITHDRAW, 1000),
            new BankAccountCommand(bankAccount, BankAccountCommand.Action.DEPOSIT, 1000)
        );
        for(BankAccountCommand l: list) {
            l.call();
            System.out.println(bankAccount);
        }
        
        System.out.println("------------------");
        Collections.reverse(list);
        for(BankAccountCommand l: list) {
            l.undo();
            System.out.println(bankAccount);
        }
    }
}
