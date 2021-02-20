package fr.lcdlv.kata.bank;

public class Account {

    public static final Money MINIMUM_MONEY_ALLOWED = Money.of(0.01);

    private final Transactions transactions;

    public Account(Transactions transactions) {
        this.transactions = transactions;
    }

    public void deposit(Money money) throws MinimumMoneyAllowedException {
        if (notAllowed(money)) {
            throw new MinimumMoneyAllowedException();
        }
        recordDepositTransaction(money);
    }

    private boolean notAllowed(Money money) {
        return money.isLessThan(MINIMUM_MONEY_ALLOWED);
    }

    private void recordDepositTransaction(Money money) {
        Transaction depositTransaction = new DepositTransaction(money);
        transactions.record(depositTransaction);
    }

    public void withdraw(Money money) throws OverdraftException {
        if (overdraft(money)) {
            throw new OverdraftException();
        }
        recordWithdrawTransaction(money);
    }

    private boolean overdraft(Money money) {
        Money balance = balance();
        return money.isBiggerThan(balance);
    }

    private void recordWithdrawTransaction(Money money) {
        Transaction withdrawTransaction = new WithdrawTransaction(money);
        transactions.record(withdrawTransaction);
    }

    public void transferTo(Account toAccount, Money money) throws OverdraftException, MinimumMoneyAllowedException {
        withdraw(money);
        toAccount.deposit(money);
    }

    public Money balance() {
        return transactions.sum();
    }

    public Transactions transactions() {
        return transactions;
    }
}
