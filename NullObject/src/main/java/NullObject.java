interface Log {
    void info(String msg);
    void warn(String msg);
}

class BankAccount {
    private Log log;
    private int balance;

    public BankAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount) {
        this.balance+=amount;
        log.info("Deposited "+amount);
    }
}

class ConsoleLog implements Log {
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println("warning "+msg);
    }
}

public class NullObject {
    public static void main(String[] args) {
        ConsoleLog log=new ConsoleLog();
        BankAccount account=new BankAccount(log); // what if we pass null ? 
        account.deposit(100);

    }
}
