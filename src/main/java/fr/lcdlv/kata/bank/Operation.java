package fr.lcdlv.kata.bank;

public interface Operation {

    Transactions apply(Transactions accountTransactions) throws OperationException;
}
