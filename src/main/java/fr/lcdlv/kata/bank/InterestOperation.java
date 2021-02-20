package fr.lcdlv.kata.bank;

public class InterestOperation implements Operation {
    private final double rate;

    public InterestOperation(double rate) {
        this.rate = rate;
    }

    @Override
    public Transaction apply(Money balance) {
        var interest = balance.multiplyBy(rate / 100);
        return new DepositTransaction(interest);
    }
}
