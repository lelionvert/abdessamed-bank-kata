package fr.lcdlv.kata.bank;

public interface Operation {
    default Transaction apply(Money balance) throws OperationException {
        return null;
    }

    default Transactions apply(Transactions accountTransactions) throws OperationException {
        Money balance = accountTransactions.sum();
        Transaction transaction = apply(balance);
        Transactions operationTransactions = new Transactions();
        operationTransactions.record(transaction);

        return operationTransactions;
    }
}
