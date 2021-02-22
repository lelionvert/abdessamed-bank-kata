package fr.lcdlv.kata.bank;

public interface ExtendedComparable<T> extends Comparable<T> {

    default boolean compareTo(T o, LogicOperation operation) {
        var result = compareTo(o);
        return operation.interpret(result);
    }

    enum LogicOperation {
        EQUAL_TO {
            @Override
            public boolean interpret(int result) {
                return result == 0;
            }
        },
        NOT_EQUAL_TO {
            @Override
            public boolean interpret(int result) {
                return result != 0;
            }
        },
        BIGGER_THAN {
            @Override
            public boolean interpret(int result) {
                return result > 0;
            }
        },
        BIGGER_THAN_OR_EQUAL_TO {
            @Override
            public boolean interpret(int result) {
                return BIGGER_THAN.interpret(result) || EQUAL_TO.interpret(result);
            }
        },
        LESS_THAN {
            @Override
            public boolean interpret(int result) {
                return result < 0;
            }
        },
        LESS_THAN_OR_EQUAL_TO {
            @Override
            public boolean interpret(int result) {
                return LESS_THAN.interpret(result) || LESS_THAN.interpret(result);
            }
        };

        public abstract boolean interpret(int result);
    }
}
