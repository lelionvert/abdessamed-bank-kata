package fr.lcdlv.kata.bank;

import static fr.lcdlv.kata.bank.ExtendedComparable.LogicOperation.LESS_THAN;

public class DepositOperation implements Operation {

    public static final Money MINIMUM_MONEY_ALLOWED = Money.of(0.01);

    private final Money amount;

    public DepositOperation(Money amount) {
        this.amount = amount;
    }

    @Override
    public Transactions apply(Transactions accountTransactions) throws OperationException {
        if (notAllowed(amount)) {
            throw new MinimumMoneyAllowedException();
        }
        Transaction transaction = new DepositTransaction(amount);
        Transactions operationTransactions = new Transactions();
        operationTransactions.record(transaction);

        return operationTransactions;
    }

    private boolean notAllowed(Money amount) {
        return amount.compareTo(MINIMUM_MONEY_ALLOWED, LESS_THAN);
    }
}
