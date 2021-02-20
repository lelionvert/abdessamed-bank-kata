package fr.lcdlv.kata.bank;

public class Account {

    private final Transactions transactions;

    public Account(Transactions transactions) {
        this.transactions = transactions;
    }

    public void apply(Operation operation) throws OperationException {
        Money balance = balance();
        Transaction transaction = operation.apply(balance);
        transactions.record(transaction);
    }

    public Money balance() {
        return transactions.sum();
    }

    public Transactions transactions() {
        return transactions;
    }
}
