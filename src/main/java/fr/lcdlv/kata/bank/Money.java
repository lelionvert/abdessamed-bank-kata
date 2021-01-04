package fr.lcdlv.kata.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money implements Comparable<Money> {

    private BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_EVEN);
    }

    public static Money of(double money) {
        return new Money(BigDecimal.valueOf(money));
    }

    public static Money of(BigDecimal money) {
        return new Money(money);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Money money = (Money) other;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public Money add(Money other) {
        return Money.of(value.add(other.value));
    }

    public int compareTo(Money other) {
        return value.compareTo(other.value);
    }

    public boolean isBiggerThanOrEqualTo(Money other) {
        return compareTo(other) >= 0;
    }

    public boolean isLessThan(Money other) {
        return compareTo(other) < 0;
    }
}
