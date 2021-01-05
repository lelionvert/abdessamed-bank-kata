package fr.lcdlv.kata.bank;

public class ReturnTrueOnEqualsStubBaseTransaction extends BaseTransaction {

    public ReturnTrueOnEqualsStubBaseTransaction() {
        super(Money.ZERO);
    }

    @Override
    public Money applyOn(Money money) {
        return Money.ZERO;
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
