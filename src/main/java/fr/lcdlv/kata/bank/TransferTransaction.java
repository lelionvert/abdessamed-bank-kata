package fr.lcdlv.kata.bank;

public class TransferTransaction implements Operation {
    private final Account toAccount;
    private final Money amount;

    public TransferTransaction(Account toAccount, Money amount) {
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public Transaction apply(Money balance) throws OperationException {
        var operation = new WithdrawOperation(amount);
        Transaction withdrawnTransaction = operation.apply(balance);

        var depositOperation = new DepositOperation(amount);
        toAccount.apply(depositOperation);

        return withdrawnTransaction;
    }
}
