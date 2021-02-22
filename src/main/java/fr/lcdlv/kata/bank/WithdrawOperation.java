package fr.lcdlv.kata.bank;

public class WithdrawOperation implements Operation {
    private final Money amount;

    public WithdrawOperation(Money amount) {
        this.amount = amount;
    }

    @Override
    public Transactions apply(Transactions accountTransactions) throws OperationException {
        Money balance = accountTransactions.sum();
        if (overdraft(balance)) {
            throw new OverdraftException();
        }
        Transaction transaction = new WithdrawTransaction(amount);
        Transactions operationTransactions = new Transactions();
        operationTransactions.record(transaction);

        return operationTransactions;
    }

    private boolean overdraft(Money balance) {
        return amount.isBiggerThan(balance);
    }
}
