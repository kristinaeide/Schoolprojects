import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserInterfaceProject extends JFrame {

    private JTextArea textArea;
    private JPanel mainPanel;
    private JMenuItem greenItem;

    private Random rng = new Random();
    private DateTimeFormatter dtf =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserInterfaceProject() {
        super("User Interface Project");

        // ----- Main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ----- Text area
        textArea = new JTextArea(10, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ----- Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem dateTimeItem =
                new JMenuItem("1) Print Date and Time");
        JMenuItem saveItem =
                new JMenuItem("2) Save Text to log.txt");
        greenItem =
                new JMenuItem("3) Random Green Background");
        JMenuItem exitItem =
                new JMenuItem("4) Exit");

        dateTimeItem.addActionListener(this::printDateTime);
        saveItem.addActionListener(this::saveToFile);
        greenItem.addActionListener(this::changeGreenBackground);
        exitItem.addActionListener(e -> System.exit(0));

        menu.add(dateTimeItem);
        menu.add(saveItem);
        menu.add(greenItem);
        menu.addSeparator();
        menu.add(exitItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    // ----- Menu Option 1
    private void printDateTime(ActionEvent e) {
        String now = LocalDateTime.now().format(dtf);
        textArea.append("Date/Time: " + now + "\n");
    }

    // ----- Menu Option 2
    private void saveToFile(ActionEvent e) {
        try {
            Files.write(
                    Path.of("log.txt"),
                    textArea.getText().getBytes(StandardCharsets.UTF_8)
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Text saved to log.txt",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving file: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ----- Menu Option 3
    private void changeGreenBackground(ActionEvent e) {
        int r = rng.nextInt(80);
        int g = 130 + rng.nextInt(126);
        int b = rng.nextInt(80);

        Color green = new Color(r, g, b);
        mainPanel.setBackground(green);

        String hex =
                String.format("#%02X%02X%02X", r, g, b);

        greenItem.setText(
                "3) Random Green Background (" + hex + ")"
        );

        textArea.append(
                "Background color changed to " + hex + "\n"
        );
    }

    // ----- MAIN METHOD (this is what Eclipse needs)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterfaceProject app =
                    new UserInterfaceProject();
            app.setVisible(true);
        });
    }
}