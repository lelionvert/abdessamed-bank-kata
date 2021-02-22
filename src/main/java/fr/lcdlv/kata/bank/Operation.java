package fr.lcdlv.kata.bank;

public interface Operation {
    Transaction apply(Money balance) throws OperationException;

    default Transactions apply(Transactions accountTransactions) throws OperationException {
        Money balance = accountTransactions.sum();
        Transaction transaction = apply(balance);
        Transactions operationTransactions = new Transactions();
        operationTransactions.record(transaction);

        return operationTransactions;
    }
}
