package fr.lcdlv.kata.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public class Money implements Comparable<Money> {

    public static final BigDecimal MINUS_ONE = BigDecimal.valueOf(-1).setScale(2, RoundingMode.HALF_EVEN);

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
        return decimalFormatter().format(value);
    }

    private DecimalFormat decimalFormatter() {
        DecimalFormat decimalFormat = new DecimalFormat("##,##€");
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumIntegerDigits(2);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat;
    }

    public Money add(Money other) {
        BigDecimal sum = value.add(other.value);
        return Money.of(sum);
    }

    public Money subtract(Money other) {
        return add(oppositeOf(other));
    }

    private Money oppositeOf(Money other) {
        BigDecimal oppositeValue = other.value.multiply(MINUS_ONE);
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
}
