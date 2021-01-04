package fr.lcdlv.kata.bank;

public class Account {

    private Money balance;

    public Account(Money balance) {

        this.balance = balance;
    }

    public void deposit(Money money) {
        balance = balance.add(money);
    }

    public Money getBalance() {
        return balance;
    }
}
