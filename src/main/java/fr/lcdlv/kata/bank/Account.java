package fr.lcdlv.kata.bank;

public class Account {

    public static final Money MINIMUM_MONEY_ALLOWED = Money.of(0.01);

    private Money balance;

    public Account(Money balance) {

        this.balance = balance;
    }

    public void deposit(Money money) throws MinimumMoneyAllowedException {
        if (isAllowed(money)) {
            throw new MinimumMoneyAllowedException();
        }
        balance = balance.add(money);
    }

    private boolean isAllowed(Money money) {
        return money.isLessThan(MINIMUM_MONEY_ALLOWED);
    }

    public Money getBalance() {
        return balance;
    }

    public void withdraw(Money money) throws OverdraftException {
        if (money.isBiggerThanOrEqualTo(balance)) {
            throw new OverdraftException();
        }
        balance = balance.subtract(money);
    }
}
