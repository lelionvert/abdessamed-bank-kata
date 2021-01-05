package fr.lcdlv.kata.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static java.util.stream.Collectors.joining;

public class Transactions {

    List<Transaction> transactions = new ArrayList<>();

    public int size() {
        return transactions.size();
    }

    public void record(Transaction transaction) {
        transactions.add(transaction);
    }

    public Money sum() {
        return transactions.stream()
                .map((t) -> t.applyOn(Money.of(0)))
                .reduce(Money.of(0), Money::add);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Transactions transactions = (Transactions) other;
        return this.transactions.equals(transactions.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactions);
    }

    @Override
    public String toString() {
        return "History size : " + size() + "\n"
                + transactions.stream()
                .map(Transaction::toString)
                .collect(joining("\n"));
    }
}
