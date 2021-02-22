package fr.lcdlv.kata.bank;

public class TransferOperation implements Operation {
    private final Account toAccount;
    private final Money amount;

    public TransferOperation(Account toAccount, Money amount) {
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public Transactions apply(Transactions accountTransactions) throws OperationException {
        Money balance = accountTransactions.sum();
        var withdrawOperation = new WithdrawOperation(amount);
        Transaction withdrawnTransaction = withdrawOperation.apply(balance);

        var depositOperation = new DepositOperation(amount);
        toAccount.apply(depositOperation);

        Transactions operationTransactions = new Transactions();
        operationTransactions.record(withdrawnTransaction);

        return operationTransactions;
    }
}
