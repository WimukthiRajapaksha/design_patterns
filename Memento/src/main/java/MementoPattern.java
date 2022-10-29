class BankAccount {
    private int balance;

    public BankAccount() {
        this.balance = balance;
    }

    public Memento deposit(int amount) {
        this.balance+=amount;
        return new Memento(balance);
    }

    public void restore(Memento m) {
        this.balance=m.getBalance();
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

class Memento {
    private int balance;

    public Memento(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }
}

public class MementoPattern {
    public static void main(String[] args) {
        BankAccount account=new BankAccount();
        Memento m1=account.deposit(100);
        Memento m2=account.deposit(30);
        Memento m3=account.deposit(100);
        Memento m4=account.deposit(90);
        System.out.println(account);
        account.restore(m2);

        System.out.println(account);
        account.restore(m1);
        System.out.println(account);
    }
}
