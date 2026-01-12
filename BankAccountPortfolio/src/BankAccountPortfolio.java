public class BankAccountPortfolio {

    public static void main(String[] args) {

        System.out.println("\n--- Test Case 1: AE / normal activity ---");
        CheckingAccount acct1 = new CheckingAccount(0.013);
        acct1.setFirstName("A");
        acct1.setLastName("E");
        acct1.setAccountId(137);

        print(acct1.deposit(700.00));
        print(acct1.withdraw(130.00));
        print(acct1.applyMonthlyInterest()); // optional “portfolio flex”
        System.out.println(acct1.buildCheckingSummary());

        System.out.println("\n--- Test Case 2: AE / overdraft test ---");
        CheckingAccount acct2 = new CheckingAccount(0.07);
        acct2.setFirstName("A");
        acct2.setLastName("E");
        acct2.setAccountId(713);

        print(acct2.deposit(70.00));
        print(acct2.withdraw(130.00)); // overdraft + fee happens
        System.out.println(acct2.buildCheckingSummary());

        System.out.println("\n--- Test Case 3: validation checks ---");
        CheckingAccount acct3 = new CheckingAccount();
        System.out.println("Set first name (blank): " + acct3.setFirstName(""));
        System.out.println("Set last name (blank): " + acct3.setLastName(" "));
        System.out.println("Set account ID (-7): " + acct3.setAccountId(-7));

        print(acct3.deposit(0));
        print(acct3.withdraw(-13));
        System.out.println(acct3.buildCheckingSummary());
    }

    // >>> IMPROVED: one output method so you can format responses consistently
    private static void print(TransactionResult result) {
        System.out.println(result);
    }
}