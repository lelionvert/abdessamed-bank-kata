package fr.lcdlv.kata.bank;

public class Account {

    public static final Money MINIMUM_MONEY_ALLOWED = Money.of(0.01);

    private final Transactions transactions;

    public Account(Transactions transactions) {
        this.transactions = transactions;
    }

    public void deposit(Money money) throws OperationException {
        var depositOperation = new DepositOperation(money);
        apply(depositOperation);
    }

    public void apply(Operation operation) throws OperationException {
        Money balance = balance();
        Transaction transaction = operation.apply(balance);
        transactions.record(transaction);
    }

    public void withdraw(Money amount) throws OverdraftException {
        if (overdraft(amount)) {
            throw new OverdraftException();
        }
        recordWithdrawTransaction(amount);
    }

    private boolean overdraft(Money amount) {
        Money balance = balance();
        return amount.isBiggerThan(balance);
    }

    private void recordWithdrawTransaction(Money amount) {
        Transaction withdrawTransaction = new WithdrawTransaction(amount);
        transactions.record(withdrawTransaction);
    }

    public void transferTo(Account toAccount, Money amount) throws OverdraftException, OperationException {
        withdraw(amount);
        toAccount.deposit(amount);
    }

    public Money balance() {
        return transactions.sum();
    }

    public Transactions transactions() {
        return transactions;
    }
}
