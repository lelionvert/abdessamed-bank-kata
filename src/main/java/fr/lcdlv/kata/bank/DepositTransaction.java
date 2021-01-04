package fr.lcdlv.kata.bank;

import java.util.Objects;

public class DepositTransaction implements Transaction {

    private Money amount;

    public DepositTransaction(Money amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositTransaction that = (DepositTransaction) o;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Deposit transaction : +" + amount;
    }
}
