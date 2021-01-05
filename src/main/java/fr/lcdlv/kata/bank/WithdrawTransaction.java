package fr.lcdlv.kata.bank;

public class WithdrawTransaction extends BaseTransaction {
    public WithdrawTransaction(Money money) {
        super(money);
    }

    @Override
    public Money applyOn(Money money) {
        return money.subtract(amount);
    }

    @Override
    public String toString() {
        return "Transaction : " + amount.opposite();
    }
}
