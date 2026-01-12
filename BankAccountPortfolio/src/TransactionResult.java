import java.math.BigDecimal;

public class TransactionResult {

    // >>> IMPROVED: structured results instead of printing in business logic
    private final boolean success;
    private final String message;
    private final BigDecimal resultingBalance;

    private TransactionResult(boolean success, String message, BigDecimal resultingBalance) {
        this.success = success;
        this.message = message;
        this.resultingBalance = resultingBalance;
    }

    public static TransactionResult ok(String message, BigDecimal balance) {
        return new TransactionResult(true, message, balance);
    }

    public static TransactionResult fail(String message, BigDecimal balance) {
        return new TransactionResult(false, message, balance);
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public BigDecimal getResultingBalance() { return resultingBalance; }

    @Override
    public String toString() {
        return (success ? "SUCCESS: " : "FAIL: ") + message + " (Balance: $" + resultingBalance + ")";
    }
}