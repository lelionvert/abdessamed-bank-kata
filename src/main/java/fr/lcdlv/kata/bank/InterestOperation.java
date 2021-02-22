package fr.lcdlv.kata.bank;

public class InterestOperation implements Operation {
    private final double rate;

    public InterestOperation(double rate) {
        this.rate = rate;
    }

    @Override
    public Transactions apply(Transactions accountTransactions) {
        Money balance = accountTransactions.sum();
        var interest = balance.multiplyBy(rate / 100);
        Transaction transaction = new DepositTransaction(interest);
        Transactions operationTransactions = new Transactions();
        operationTransactions.record(transaction);

        return operationTransactions;
    }
}
