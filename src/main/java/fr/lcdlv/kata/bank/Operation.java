package fr.lcdlv.kata.bank;

public interface Operation {
    Transaction apply(Money balance) throws OperationException;
}
