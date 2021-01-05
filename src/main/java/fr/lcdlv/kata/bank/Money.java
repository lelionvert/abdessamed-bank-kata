package fr.lcdlv.kata.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public class Money implements Comparable<Money> {

    private static final BigDecimal MINUS_ONE = BigDecimal.valueOf(-1).setScale(2, RoundingMode.HALF_EVEN);
    public static final Money ZERO = Money.of(0);

    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_EVEN);
    }

    public static Money of(double money) {
        return new Money(BigDecimal.valueOf(money));
    }

    public static Money of(BigDecimal money) {
        return new Money(money);
    }

    public Money add(Money other) {
        BigDecimal sum = value.add(other.value);
        return Money.of(sum);
    }

    public Money subtract(Money other) {
        return add(other.opposite());
    }

    public Money opposite() {
        BigDecimal oppositeValue = value.multiply(MINUS_ONE);
        return Money.of(oppositeValue);
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

    public boolean isLessThanOrEqualTo(Money other) {
        return compareTo(other) <= 0;
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
        return decimalFormatter().format(value);
    }

    private DecimalFormat decimalFormatter() {
        DecimalFormat decimalFormat = new DecimalFormat("##,##€");
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMinimumIntegerDigits(1);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat;
    }
}
