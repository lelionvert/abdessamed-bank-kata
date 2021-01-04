package fr.lcdlv.kata.bank;

public class ReturnTrueOnEqualsStubTransaction implements Transaction {

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
