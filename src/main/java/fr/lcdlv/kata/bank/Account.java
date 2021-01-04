package fr.lcdlv.kata.bank;

public class Account {

    public static final Money MINIMUM_MONEY_ALLOWED = Money.of(0.01);

    private Money balance;
    private History history;

    public Account(Money balance, History history) {
        this.balance = balance;
        this.history = history;
    }

    public void deposit(Money money) throws MinimumMoneyAllowedException {
        if (isAllowed(money)) {
            throw new MinimumMoneyAllowedException();
        }
        balance = balance.add(money);
        Transaction transaction = new DepositTransaction(money);
        history.record(transaction);
    }

    private boolean isAllowed(Money money) {
        return money.isLessThan(MINIMUM_MONEY_ALLOWED);
    }

    public void withdraw(Money money) throws OverdraftException {
        if (isOverdraft(money)) {
            throw new OverdraftException();
        }
        balance = balance.subtract(money);
        Transaction transaction = new WithdrawTransaction(money);
        history.record(transaction);
    }

    private boolean isOverdraft(Money money) {
        return money.isBiggerThanOrEqualTo(balance);
    }

    public Money getBalance() {
        return balance;
    }

    public History getHistory() {
        return history;
    }
}
