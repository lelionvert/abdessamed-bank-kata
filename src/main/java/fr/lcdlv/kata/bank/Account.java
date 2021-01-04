package fr.lcdlv.kata.bank;

public class Account {

    private Money balance;

    public Account() {
        balance = Money.of(0);
    }

    public Account(Money balance) {

        this.balance = balance;
    }

    public void deposit(Money money) {

    }

    public Money getBalance() {
        return balance;
    }
}
