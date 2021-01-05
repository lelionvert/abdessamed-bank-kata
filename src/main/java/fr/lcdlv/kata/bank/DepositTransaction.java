package fr.lcdlv.kata.bank;

public class DepositTransaction extends BaseTransaction {
    public DepositTransaction(Money amount) {
        super(amount);
    }

    @Override
    public Money applyOn(Money money) {
        return money.add(amount);
    }
}
