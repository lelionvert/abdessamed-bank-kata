package fr.lcdlv.kata.bank;

public interface Transaction {

    Money applyOn(Money money);

    default Money amount() {
        return applyOn(Money.ZERO);
    }
}
