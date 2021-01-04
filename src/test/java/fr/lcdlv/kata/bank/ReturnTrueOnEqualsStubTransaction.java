package fr.lcdlv.kata.bank;

public class ReturnTrueOnEqualsStubTransaction extends Transaction {

    public ReturnTrueOnEqualsStubTransaction() {
        super(Money.of(0));
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
