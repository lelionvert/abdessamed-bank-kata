package fr.lcdlv.kata.bank;

public class Account {

    private final Transactions transactions;

    public Account(Transactions transactions) {
        this.transactions = transactions;
    }

    public void apply(Operation operation) throws OperationException {
        Transactions operationTransactions = operation.apply(transactions);
        transactions.record(operationTransactions);
    }

    public Money balance() {
        return transactions.sum();
    }

    public Transactions transactions() {
        return transactions;
    }
}
