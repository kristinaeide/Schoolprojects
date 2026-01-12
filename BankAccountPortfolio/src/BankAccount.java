import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount {

    private String firstName;
    private String lastName;
    private int accountId;

    // >>> IMPROVED: BigDecimal for money (avoids floating point issues)
    private BigDecimal balance = money(0);

    // >>> IMPROVED: small helper to standardize money formatting + rounding
    protected static BigDecimal money(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }

    // >>> IMPROVED: centralized name cleanup
    private String cleanName(String value) {
        if (value == null) return null;
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    public boolean setFirstName(String firstName) {
        String cleaned = cleanName(firstName);
        if (cleaned == null) return false;
        this.firstName = cleaned;
        return true;
    }

    public boolean setLastName(String lastName) {
        String cleaned = cleanName(lastName);
        if (cleaned == null) return false;
        this.lastName = cleaned;
        return true;
    }

    public boolean setAccountId(int accountId) {
        if (accountId <= 0) return false;
        this.accountId = accountId;
        return true;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAccountId() { return accountId; }

    public BigDecimal getBalance() {
        return balance;
    }

    // >>> IMPROVED: one “safe” way to change balance from subclasses
    protected void addToBalance(BigDecimal delta) {
        balance = balance.add(delta).setScale(2, RoundingMode.HALF_UP);
    }

    // >>> IMPROVED: returns TransactionResult instead of printing inside the class
    public TransactionResult deposit(double amount) {
        BigDecimal amt = money(amount);

        if (amt.compareTo(money(0)) <= 0) {
            return TransactionResult.fail("Deposit rejected: amount must be greater than $0.00.", balance);
        }

        addToBalance(amt);
        return TransactionResult.ok("Deposit accepted: +" + formatMoney(amt), balance);
    }

    // >>> IMPROVED: base withdraw does NOT allow overdraft (checking will override)
    public TransactionResult withdraw(double amount) {
        BigDecimal amt = money(amount);

        if (amt.compareTo(money(0)) <= 0) {
            return TransactionResult.fail("Withdrawal rejected: amount must be greater than $0.00.", balance);
        }

        if (balance.compareTo(amt) < 0) {
            return TransactionResult.fail(
                    "Withdrawal rejected: insufficient funds for " + formatMoney(amt) + ".",
                    balance
            );
        }

        addToBalance(amt.negate());
        return TransactionResult.ok("Withdrawal accepted: -" + formatMoney(amt), balance);
    }

    // >>> IMPROVED: reusable formatted summary (string-based, good for GUI later too)
    public String buildSummary() {
        String fullName = ((firstName == null) ? "" : firstName) + " " + ((lastName == null) ? "" : lastName);
        fullName = fullName.trim();

        String nameLine = fullName.isEmpty() ? "(name not set)" : fullName;
        String idLine = (accountId <= 0) ? "(id not set)" : String.valueOf(accountId);

        return "================================\n" +
               "BANK ACCOUNT\n" +
               "Name      : " + nameLine + "\n" +
               "Account ID: " + idLine + "\n" +
               "Balance   : " + formatMoney(balance) + "\n" +
               "================================";
    }

    protected String formatMoney(BigDecimal amount) {
        return "$" + amount.setScale(2, RoundingMode.HALF_UP);
    }
}