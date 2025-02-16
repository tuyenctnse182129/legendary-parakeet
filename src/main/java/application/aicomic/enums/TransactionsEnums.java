package application.aicomic.enums;

public enum TransactionsEnums {
    CANCELED((byte) 1),
    NOT_PAID((byte) 2),
    PROCESSING((byte) 3),
    PAID((byte) 4),
    FAILED((byte) 5);

    private final byte value;

    TransactionsEnums(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
    public static TransactionsEnums fromValue(byte value) {
        for (TransactionsEnums x : TransactionsEnums.values()) {
            if (x.getValue() == value) {
                return x;
            }
        }
        throw new IllegalArgumentException("No TransactionsEnums with value " + value);
    }
}
