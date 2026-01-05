import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankBalanceApp extends JFrame {

    // -------- BankAccount class --------
    static class BankAccount {
        private double balance;

        public BankAccount(double startingBalance) {
            balance = startingBalance;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }

    // -------- GUI Components --------
    private BankAccount account;

    private JLabel balanceLabel;
    private JTextField startBalanceField;
    private JTextField amountField;

    private JButton setBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton exitButton;

    // -------- Constructor --------
    public BankBalanceApp() {
        setTitle("GUI Bank Balance Application");
        setSize(500, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        balanceLabel = new JLabel("Balance: $0.00");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        startBalanceField = new JTextField();
        amountField = new JTextField();

        setBalanceButton = new JButton("Set Balance");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        exitButton = new JButton("Exit");

        depositButton.setEnabled(false);
        withdrawButton.setEnabled(false);

        panel.add(new JLabel("Enter Starting Balance:"));
        panel.add(startBalanceField);

        panel.add(new JLabel("Enter Deposit / Withdraw Amount:"));
        panel.add(amountField);

        panel.add(setBalanceButton);
        panel.add(new JLabel(""));

        panel.add(depositButton);
        panel.add(withdrawButton);

        panel.add(balanceLabel);
        panel.add(exitButton);

        add(panel);

        ButtonHandler handler = new ButtonHandler();
        setBalanceButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        exitButton.addActionListener(handler);
    }

    // -------- Helper Methods --------
    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("Balance: $%.2f", account.getBalance()));
    }

    private Double readAmount(JTextField field, String fieldName) {
        String text = field.getText().trim();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    fieldName + " cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            double value = Double.parseDouble(text);

            if (value < 0) {
                JOptionPane.showMessageDialog(this,
                        fieldName + " cannot be negative.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }

            return value;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number for " + fieldName + ".",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // -------- ActionListener --------
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == setBalanceButton) {
                Double startingBalance = readAmount(startBalanceField, "Starting Balance");
                if (startingBalance == null) return;

                account = new BankAccount(startingBalance);
                updateBalanceLabel();

                depositButton.setEnabled(true);
                withdrawButton.setEnabled(true);

                JOptionPane.showMessageDialog(BankBalanceApp.this,
                        "Balance set successfully!");
            }

            else if (e.getSource() == depositButton) {
                Double amount = readAmount(amountField, "Deposit Amount");
                if (amount == null) return;

                account.deposit(amount);
                updateBalanceLabel();
                amountField.setText("");

                JOptionPane.showMessageDialog(BankBalanceApp.this,
                        "Deposit successful.");
            }

            else if (e.getSource() == withdrawButton) {
                Double amount = readAmount(amountField, "Withdraw Amount");
                if (amount == null) return;

                if (!account.withdraw(amount)) {
                    JOptionPane.showMessageDialog(BankBalanceApp.this,
                            "Insufficient funds.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updateBalanceLabel();
                amountField.setText("");

                JOptionPane.showMessageDialog(BankBalanceApp.this,
                        "Withdrawal successful.");
            }

            else if (e.getSource() == exitButton) {
                if (account != null) {
                    JOptionPane.showMessageDialog(BankBalanceApp.this,
                            String.format("Final Balance: $%.2f", account.getBalance()));
                } else {
                    JOptionPane.showMessageDialog(BankBalanceApp.this,
                            "No balance was set.");
                }
                System.exit(0);
            }
        }
    }

    // -------- MAIN METHOD (THIS IS WHAT ECLIPSE NEEDS) --------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankBalanceApp app = new BankBalanceApp();
            app.setVisible(true);
        });
    }
}
