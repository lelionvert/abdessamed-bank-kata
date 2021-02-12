package fr.lcdlv.kata.bank;

public class Account {

    public static final Money MINIMUM_MONEY_ALLOWED = Money.of(0.01);

    private final Transactions transactions;

    public Account(Transactions transactions) {
        this.transactions = transactions;
    }

    public void deposit(Money money) throws MinimumMoneyAllowedException {
        if (isAllowed(money)) {
            throw new MinimumMoneyAllowedException();
        }
        recordDepositTransaction(money);
    }

    private boolean isAllowed(Money money) {
        return money.isLessThan(MINIMUM_MONEY_ALLOWED);
    }

    private void recordDepositTransaction(Money money) {
        Transaction depositTransaction = new DepositTransaction(money);
        transactions.record(depositTransaction);
    }

    public void withdraw(Money money) throws OverdraftException {
        if (isOverdraft(money)) {
            throw new OverdraftException();
        }
        recordWithdrawTransaction(money);
    }

    private boolean isOverdraft(Money money) {
        Money balance = getBalance();
        return money.isBiggerThanOrEqualTo(balance);
    }

    private void recordWithdrawTransaction(Money money) {
        Transaction withdrawTransaction = new WithdrawTransaction(money);
        transactions.record(withdrawTransaction);
    }

    public Money getBalance() {
        return transactions.sum();
    }

    public Transactions getTransactions() {
        return transactions;
    }
}
