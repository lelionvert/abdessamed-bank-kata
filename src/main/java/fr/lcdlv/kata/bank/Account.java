package fr.lcdlv.kata.bank;

public class Account {

    private Money balance;

    public Account(Money balance) {

        this.balance = balance;
    }

    public void deposit(Money money) throws MinimumMoneyException {
        if(money.isLessThan(Money.of(0.01))) {
            throw new MinimumMoneyException();
        }
        balance = balance.add(money);
    }

    public Money getBalance() {
        return balance;
    }
}
