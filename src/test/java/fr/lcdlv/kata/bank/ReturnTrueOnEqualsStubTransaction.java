package fr.lcdlv.kata.bank;

public class ReturnTrueOnEqualsStubTransaction extends BaseTransaction {

    public ReturnTrueOnEqualsStubTransaction() {
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

    @Override
    public String toString() {
        return "Transaction : " + amount;
    }
}
