package fr.lcdlv.kata.bank;

public class Account {

    public static final Money MINIMUM_MONEY_ALLOWED = Money.of(0.01);

    private Money balance;

    public Account(Money balance) {

        this.balance = balance;
    }

    public void deposit(Money money) throws MinimumMoneyException {
        if(money.isLessThan(MINIMUM_MONEY_ALLOWED)) {
            throw new MinimumMoneyException();
        }
        balance = balance.add(money);
    }

    public Money getBalance() {
        return balance;
    }
}
