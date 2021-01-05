package fr.lcdlv.kata.bank;

import java.util.Objects;

public abstract class BaseTransaction implements Transaction {

    protected Money amount;

    public BaseTransaction(Money amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTransaction that = (BaseTransaction) o;
        return amount.equals(that.amount);
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
