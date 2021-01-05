package fr.lcdlv.kata.bank;

public class Account {

    public static final Money MINIMUM_MONEY_ALLOWED = Money.of(0.01);

    private Transactions transactions;

    public Account(Transactions transactions) {
        this.transactions = transactions;
    }

    public void deposit(Money money) throws MinimumMoneyAllowedException {
        if (isAllowed(money)) {
            throw new MinimumMoneyAllowedException();
        }
        recordDepositTransaction(money);
    }

    private void recordDepositTransaction(Money money) {
        Transaction depositTransaction = new DepositTransaction(money);
        transactions.record(depositTransaction);
    }

    private boolean isAllowed(Money money) {
        return money.isLessThan(MINIMUM_MONEY_ALLOWED);
    }

    public void withdraw(Money money) throws OverdraftException {
        if (isOverdraft(money)) {
            throw new OverdraftException();
        }
        recordWithdrawTransaction(money);
    }

    private void recordWithdrawTransaction(Money money) {
        Transaction withdrawTransaction = new WithdrawTransaction(money);
        transactions.record(withdrawTransaction);
    }

    private boolean isOverdraft(Money money) {
        Money balance = getBalance();
        return money.isBiggerThanOrEqualTo(balance);
    }

    public Money getBalance() {
        return transactions.sum();
    }

    public Transactions getHistory() {
        return transactions;
    }
}
