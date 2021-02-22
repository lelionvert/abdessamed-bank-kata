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
        var withdrawOperation = new WithdrawOperation(amount);
        Transactions withdrawnTransactions = withdrawOperation.apply(accountTransactions);

        var depositOperation = new DepositOperation(amount);
        toAccount.apply(depositOperation);

        return withdrawnTransactions;
    }
}
