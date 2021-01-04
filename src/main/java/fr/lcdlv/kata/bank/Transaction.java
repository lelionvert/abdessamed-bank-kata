package fr.lcdlv.kata.bank;

import java.util.Objects;

public class Transaction {

    private Money amount;

    public Transaction(Money amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
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
