package fr.lcdlv.kata.bank;

public class DepositOperation implements Operation{

    public static final Money MINIMUM_MONEY_ALLOWED = Money.of(0.01);

    private Money amount;

    public DepositOperation(Money amount) throws MinimumMoneyAllowedException {
        if (notAllowed(amount)) {
            throw new MinimumMoneyAllowedException();
        }
        this.amount = amount;
    }

    @Override
    public Transaction apply(Money balance) {
        return new DepositTransaction(amount);
    }

    private boolean notAllowed(Money amount) {
        return amount.isLessThan(MINIMUM_MONEY_ALLOWED);
    }
}
