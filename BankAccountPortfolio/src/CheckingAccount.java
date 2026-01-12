import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {

    // >>> IMPROVED: constants instead of magic numbers
    private static final BigDecimal OVERDRAFT_FEE = money(30);

    private double interestRate; // example: 0.013 for 1.3%

    public CheckingAccount() {
        super();
        this.interestRate = 0.0;
    }

    public CheckingAccount(double interestRate) {
        super();
        setInterestRate(interestRate);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public boolean setInterestRate(double interestRate) {
        if (interestRate < 0) return false;
        this.interestRate = interestRate;
        return true;
    }

    // >>> IMPROVED: override withdraw with overdraft rules (unique behavior by account type)
    @Override
    public TransactionResult withdraw(double amount) {
        BigDecimal amt = money(amount);

        if (amt.compareTo(money(0)) <= 0) {
            return TransactionResult.fail("Withdrawal rejected: amount must be greater than $0.00.", getBalance());
        }

        // Checking allows overdraft:
        addToBalance(amt.negate());

        // If negative after withdrawal, apply fee:
        if (getBalance().compareTo(money(0)) < 0) {
            addToBalance(OVERDRAFT_FEE.negate());

            return TransactionResult.ok(
                    "Withdrawal processed with overdraft. Fee applied: -" + formatMoney(OVERDRAFT_FEE),
                    getBalance()
            );
        }

        return TransactionResult.ok("Withdrawal accepted: -" + formatMoney(amt), getBalance());
    }

    // >>> IMPROVED: optional “portfolio flex” method (not required, but shows design)
    public TransactionResult applyMonthlyInterest() {
        if (interestRate <= 0) {
            return TransactionResult.fail("No interest applied (interest rate is 0).", getBalance());
        }

        // Only apply interest if balance is positive (common rule)
        if (getBalance().compareTo(money(0)) <= 0) {
            return TransactionResult.fail("No interest applied (balance is not positive).", getBalance());
        }

        BigDecimal interest = getBalance().multiply(BigDecimal.valueOf(interestRate));
        interest = interest.setScale(2, java.math.RoundingMode.HALF_UP);

        addToBalance(interest);
        return TransactionResult.ok("Monthly interest applied: +" + formatMoney(interest), getBalance());
    }

    public String buildCheckingSummary() {
        return buildSummary() +
               String.format("%nChecking Interest Rate: %.2f%%%n", interestRate * 100);
    }
}