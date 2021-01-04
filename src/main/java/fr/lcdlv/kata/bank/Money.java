package fr.lcdlv.kata.bank;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public static Money of(double money) {
        return new Money(BigDecimal.valueOf(money));
    }

    public static Money of(BigDecimal money) {
        return new Money(money);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
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

    public Money add(Money money) {
        return Money.of(value.add(money.value));
    }
}
