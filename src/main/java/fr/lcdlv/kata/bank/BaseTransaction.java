package fr.lcdlv.kata.bank;

import java.util.Objects;

public abstract class BaseTransaction implements Transaction {

    protected Money amount;

    public BaseTransaction(Money amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        BaseTransaction otherTransaction = (BaseTransaction) other;
        return amount.equals(otherTransaction.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Transaction : " + amount;
    }
}
