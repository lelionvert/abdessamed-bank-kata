package fr.lcdlv.kata.bank;

import static fr.lcdlv.kata.bank.ExtendedComparable.LogicOperation.BIGGER_THAN;

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
        return amount.compareTo(balance, BIGGER_THAN);
    }
}
