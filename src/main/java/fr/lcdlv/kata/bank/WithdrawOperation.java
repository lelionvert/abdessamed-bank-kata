package fr.lcdlv.kata.bank;

public class WithdrawOperation implements Operation {
    private final Money amount;

    public WithdrawOperation(Money amount) {
        this.amount = amount;
    }

    @Override
    public Transaction apply(Money balance) throws OperationException {
        if (overdraft(balance)) {
            throw new OverdraftException();
        }
        return new WithdrawTransaction(amount);
    }

    private boolean overdraft(Money balance) {
        return amount.isBiggerThan(balance);
    }
}
