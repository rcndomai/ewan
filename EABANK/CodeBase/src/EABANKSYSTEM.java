import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class EABANKSYSTEM {
    private static JFrame frame;
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

    // Store account info captured via popups when Credit/Debit Card selected
    private static String accountNameInput = "";
    private static String accountNumberInput = "";

    public static void main(String[] args) {
        frame = new JFrame("MKA EABANK SYSTEM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        showLoginOrRegisterMenu();
        frame.setVisible(true);
    }

    // Reusable panel with background image for all screens
    static class BackgroundPanel extends JPanel {
        private Image bg;

        public BackgroundPanel() {
            this.bg = new ImageIcon("bg3.jpg").getImage(); // Make sure bg3.jpg is in the same folder as your .java file
            setOpaque(false);
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private static void showLoginOrRegisterMenu() {
        JPanel panel = new BackgroundPanel();

        JLabel title = new JLabel("MKA", SwingConstants.CENTER);
        title.setFont(new Font("Arial Black", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(186, 75, 100, 30);

        JLabel subtitle = new JLabel("EABANK SYSTEM", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.BOLD, 22));
        subtitle.setForeground(Color.WHITE);
        subtitle.setBounds(140, 125, 200, 30);

        JButton loginButton = createButton("Login", "login.jpg");
        loginButton.setBounds(125, 227, 240, 60);

        JButton registerButton = createButton("Register", "register.jpg");
        registerButton.setBounds(125, 315, 240, 60);

        JButton exitButton = createButton("Exit", "exit.jpg");
        exitButton.setBounds(125, 400, 240, 60);

        panel.add(title);
        panel.add(subtitle);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(exitButton);

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();

        loginButton.addActionListener(e -> showLoginMenu());
        registerButton.addActionListener(e -> showRegisterMenu());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private static JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBackground(new Color(230, 230, 255));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        try {
            ImageIcon originalIcon = new ImageIcon(iconPath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.out.println("Could not load icon: " + iconPath);
        }

        return button;
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(230, 230, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return button;
    }

    private static void showLoginMenu() {
        JPanel panel = new BackgroundPanel();

        JLabel heading = new JLabel("MKA", SwingConstants.CENTER);
        heading.setFont(new Font("Arial Black", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);
        heading.setBounds(186, 75, 100, 30);

        JLabel subheading = new JLabel("EABANK SYSTEM", SwingConstants.CENTER);
        subheading.setFont(new Font("Arial", Font.BOLD, 22));
        subheading.setForeground(Color.WHITE);
        subheading.setBounds(140, 125, 200, 30);

        JLabel mobileLabel = new JLabel("Mobile Number:");
        JTextField mobileField = new JTextField();

        JLabel pinLabel = new JLabel("PIN:");
        JPasswordField pinField = new JPasswordField();
        JCheckBox showPinCheckBox = new JCheckBox("Show PIN");
        showPinCheckBox.setOpaque(false);

        JButton loginButton = createStyledButton("Login");
        JButton backButton = createStyledButton("Back");

        int labelWidth = 140;
        int fieldWidth = 220;
        int baseX = 65;
        int baseY = 230;
        int verticalGap = 50;

        mobileLabel.setBounds(baseX, baseY, labelWidth, 25);
        mobileField.setBounds(baseX + labelWidth, baseY, fieldWidth, 25);

        pinLabel.setBounds(baseX, baseY + verticalGap, labelWidth, 25);
        pinField.setBounds(baseX + labelWidth, baseY + verticalGap, fieldWidth, 25);

        showPinCheckBox.setBounds(baseX + labelWidth, baseY + verticalGap + 30, 120, 25);

        loginButton.setBounds(baseX + 40, baseY + verticalGap * 2 + 40, 120, 40);
        backButton.setBounds(baseX + 189, baseY + verticalGap * 2 + 40, 120, 40);

        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        for (JLabel label : new JLabel[] { mobileLabel, pinLabel }) {
            label.setForeground(Color.WHITE);
            label.setFont(labelFont);
        }

        showPinCheckBox.setForeground(Color.WHITE);
        showPinCheckBox.setFont(labelFont);

        pinField.setEchoChar('•');

        showPinCheckBox.addActionListener(e -> {
            pinField.setEchoChar(showPinCheckBox.isSelected() ? (char) 0 : '•');
        });

        panel.add(heading);
        panel.add(subheading);
        panel.add(mobileLabel);
        panel.add(mobileField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(showPinCheckBox);
        panel.add(loginButton);
        panel.add(backButton);

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();

        loginButton.addActionListener(e -> {
            String mobile = mobileField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();
            if (!mobile.isEmpty() && !pin.isEmpty()) {
                User user = findUserByMobile(mobile);
                if (user != null && user.checkPin(pin)) {
                    currentUser = user;
                    showMainMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid mobile number or PIN.", "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter both mobile number and PIN.", "Input Required",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        backButton.addActionListener(e -> showLoginOrRegisterMenu());
    }

    private static void showRegisterMenu() {
        JPanel panel = new BackgroundPanel();

        JLabel heading2 = new JLabel("MKA", SwingConstants.CENTER);
        heading2.setFont(new Font("Arial Black", Font.BOLD, 30));
        heading2.setForeground(Color.WHITE);
        heading2.setBounds(186, 75, 100, 30);

        JLabel subheading2 = new JLabel("EABANK SYSTEM", SwingConstants.CENTER);
        subheading2.setFont(new Font("Arial", Font.BOLD, 22));
        subheading2.setForeground(Color.WHITE);
        subheading2.setBounds(140, 125, 200, 30);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel mobileLabel = new JLabel("Mobile Number:");
        JTextField mobileField = new JTextField();

        JLabel pinLabel = new JLabel("Create 4-digit PIN:");
        JPasswordField pinField = new JPasswordField();

        JCheckBox showPinCheckBox = new JCheckBox("Show PIN");
        showPinCheckBox.setOpaque(false);

        JButton registerButton = createStyledButton("Register");
        JButton backButton = createStyledButton("Back");

        int labelWidth = 140;
        int fieldWidth = 220;
        int baseX = 65;
        int baseY = 230;
        int verticalGap = 50;

        nameLabel.setBounds(baseX, baseY, labelWidth, 25);
        nameField.setBounds(baseX + labelWidth, baseY, fieldWidth, 25);

        mobileLabel.setBounds(baseX, baseY + verticalGap, labelWidth, 25);
        mobileField.setBounds(baseX + labelWidth, baseY + verticalGap, fieldWidth, 25);

        pinLabel.setBounds(baseX, baseY + verticalGap * 2, labelWidth, 25);
        pinField.setBounds(baseX + labelWidth, baseY + verticalGap * 2, fieldWidth, 25);

        showPinCheckBox.setBounds(baseX + labelWidth, baseY + verticalGap * 2 + 30, 120, 25);

        registerButton.setBounds(baseX + 40, baseY + verticalGap * 3 + 40, 120, 40);
        backButton.setBounds(baseX + 190, baseY + verticalGap * 3 + 40, 120, 40);

        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        for (JLabel label : new JLabel[] { nameLabel, mobileLabel, pinLabel }) {
            label.setForeground(Color.WHITE);
            label.setFont(labelFont);
        }

        showPinCheckBox.setForeground(Color.WHITE);
        showPinCheckBox.setFont(labelFont);

        pinField.setEchoChar('•');

        showPinCheckBox.addActionListener(e -> {
            pinField.setEchoChar(showPinCheckBox.isSelected() ? (char) 0 : '•');
        });

        panel.add(heading2);
        panel.add(subheading2);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(mobileLabel);
        panel.add(mobileField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(showPinCheckBox);
        panel.add(registerButton);
        panel.add(backButton);

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();

        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String mobile = mobileField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();

            if (!name.isEmpty() && !mobile.isEmpty() && !pin.isEmpty()) {
                if (pin.length() != 4 || !pin.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(frame, "PIN must be exactly 4 digits.", "Invalid PIN",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (findUserByMobile(mobile) == null) {
                    // Generate OTP
                    String otp = generateOTP();
                    JOptionPane.showMessageDialog(frame,
                            "Your OTP code is: " + otp + "\nPlease enter this code to verify your account.",
                            "OTP Verification", JOptionPane.INFORMATION_MESSAGE);

                    String inputOtp = JOptionPane.showInputDialog(frame, "Enter OTP:");
                    if (inputOtp != null && inputOtp.trim().equals(otp)) {
                        currentUser = new User(name, mobile, pin);
                        users.add(currentUser);
                        JOptionPane.showMessageDialog(frame, "Registration successful! You can now log in.", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        showLoginOrRegisterMenu();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect OTP. Registration cancelled.",
                                "Verification Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Mobile number already registered.", "Registration Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Required",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        backButton.addActionListener(e -> showLoginOrRegisterMenu());
    }

    private static String generateOTP() {
        Random rand = new Random();
        int otpNum = 100000 + rand.nextInt(900000);
        return String.valueOf(otpNum);
    }

    private static void showMainMenu() {
        JPanel panel = new BackgroundPanel();

        JLabel welcomeLabel = new JLabel("Hello, " + currentUser.getName() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial Black", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(-10, 85, 500, 40);
        panel.add(welcomeLabel);

        String[][] buttons = {
                { "Cash In", "cashin.jpg" },
                { "Cash Out", "cashout.jpg" },
                { "Transfer", "transfer.jpg" },
                { "Check Balance", "balance.jpg" },
                { "Transaction History", "history.jpg" },
                { "Money Exchanger", "exchange.jpg" },
                { "Logout", "logout.jpg" }
        };

        int y = 150;

        for (String[] btnData : buttons) {
            String text = btnData[0];
            String icon = btnData[1];

            JButton btn = createButton(text, icon);
            btn.setBounds(95, y, 300, 50);
            panel.add(btn);
            y += 60;

            switch (text) {
                case "Cash In" -> btn.addActionListener(e -> cashIn());
                case "Cash Out" -> btn.addActionListener(e -> cashOut());
                case "Transfer" -> btn.addActionListener(e -> transfer());
                case "Check Balance" -> btn.addActionListener(e -> checkBalance());
                case "Transaction History" -> btn.addActionListener(e -> viewTransactionHistory());
                case "Money Exchanger" -> btn.addActionListener(e -> showMoneyExchanger());
                case "Logout" -> btn.addActionListener(e -> {
                    currentUser = null;
                    showLoginOrRegisterMenu();
                });
            }
        }

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    private static JComboBox<String> methodComboBox;
    private static JButton confirmButton;
    private static JButton backButton;

    private static void cashIn() {
        JPanel panel = new BackgroundPanel();

        JLabel heading3 = new JLabel("MKA", SwingConstants.CENTER);
        heading3.setFont(new Font("Arial Black", Font.BOLD, 30));
        heading3.setForeground(Color.WHITE);
        heading3.setBounds(186, 75, 100, 30);

        JLabel subheading3 = new JLabel("EABANK SYSTEM", SwingConstants.CENTER);
        subheading3.setFont(new Font("Arial", Font.BOLD, 22));
        subheading3.setForeground(Color.WHITE);
        subheading3.setBounds(140, 125, 200, 30);

        JLabel methodLabel = new JLabel("Select Payment Method:");
        String[] methods = { "Credit/Debit Card", "EABANK Machine", "7-Eleven", "Uncle John", "SM Outlets" };
        methodComboBox = new JComboBox<>(methods);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        JLabel currencyLabel = new JLabel("Currency:");
        String[] currencies = { "PHP (₱)", "USD ($)", "JPY (¥)" };
        JComboBox<String> currencyComboBox = new JComboBox<>(currencies);

        JLabel accountHolderLabel = new JLabel("Account Holder:");
        JTextField accountHolderField = new JTextField();

        JLabel accountNumberLabel = new JLabel("Account Number:");
        JTextField accountNumberField = new JTextField();

        accountHolderLabel.setVisible(false);
        accountHolderField.setVisible(false);
        accountNumberLabel.setVisible(false);
        accountNumberField.setVisible(false);

        confirmButton = createStyledButton("Confirm");
        backButton = createStyledButton("Back");

        int labelWidth = 140;
        int fieldWidth = 220;
        int baseX = 75;
        int baseY = 220;
        int verticalGap = 75;

        methodLabel.setBounds(baseX, baseY, labelWidth, 25);
        methodComboBox.setBounds(baseX + labelWidth, baseY, fieldWidth, 25);

        amountLabel.setBounds(baseX, baseY + verticalGap, labelWidth, 25);
        amountField.setBounds(baseX + labelWidth, baseY + verticalGap, fieldWidth, 25);

        currencyLabel.setBounds(baseX, baseY + verticalGap * 2, labelWidth, 25);
        currencyComboBox.setBounds(baseX + labelWidth, baseY + verticalGap * 2, fieldWidth, 25);

        accountHolderLabel.setBounds(baseX, baseY + verticalGap * 3, labelWidth, 25);
        accountHolderField.setBounds(baseX + labelWidth, baseY + verticalGap * 3, fieldWidth, 25);

        accountNumberLabel.setBounds(baseX, baseY + verticalGap * 4, labelWidth, 25);
        accountNumberField.setBounds(baseX + labelWidth, baseY + verticalGap * 4, fieldWidth, 25);

        confirmButton.setBounds(baseX + 40, baseY + verticalGap * 5, 120, 40);
        backButton.setBounds(baseX + 180, baseY + verticalGap * 5, 120, 40);

        Font labelFont = new Font("Arial", Font.PLAIN, 12);
        for (JLabel label : new JLabel[] { methodLabel, amountLabel, currencyLabel, accountHolderLabel,
                accountNumberLabel }) {
            label.setForeground(Color.WHITE);
            label.setFont(labelFont);
        }

        panel.setLayout(null);
        panel.add(heading3);
        panel.add(subheading3);
        panel.add(methodLabel);
        panel.add(methodComboBox);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(currencyLabel);
        panel.add(currencyComboBox);
        panel.add(accountHolderLabel);
        panel.add(accountHolderField);
        panel.add(accountNumberLabel);
        panel.add(accountNumberField);
        panel.add(confirmButton);
        panel.add(backButton);

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();

        // Show/hide account fields based on payment method
        methodComboBox.addActionListener(e -> {
            boolean showFields = methodComboBox.getSelectedItem().equals("Credit/Debit Card");
            accountHolderLabel.setVisible(showFields);
            accountHolderField.setVisible(showFields);
            accountNumberLabel.setVisible(showFields);
            accountNumberField.setVisible(showFields);
            panel.repaint();

            if (showFields) {
                confirmButton.setBounds(baseX + 40, baseY + verticalGap * 5, 120, 40);
                backButton.setBounds(baseX + 180, baseY + verticalGap * 5, 120, 40);
            } else {
                confirmButton.setBounds(baseX + 40, baseY + verticalGap * 3 + 20, 120, 40);
                backButton.setBounds(baseX + 180, baseY + verticalGap * 3 + 20, 120, 40);
            }

            panel.revalidate();
            panel.repaint();
        });

        confirmButton.addActionListener(e -> {
            String amountText = amountField.getText().trim();
            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter an amount.", "Input Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(amountText);
                if (amount <= 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectedCurrency = (String) currencyComboBox.getSelectedItem();
            String selectedMethod = (String) methodComboBox.getSelectedItem();

            // Save account info inputs if visible
            if ("Credit/Debit Card".equals(selectedMethod)) {
                accountNameInput = accountHolderField.getText().trim();
                accountNumberInput = accountNumberField.getText().trim();

                if (accountNameInput.isEmpty() || accountNumberInput.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter account name and number by selecting Credit/Debit Card payment method.",
                            "Input Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                // Clear stored account info if not Credit/Debit Card selected
                accountNameInput = "";
                accountNumberInput = "";
            }

            CashInTransaction action = new CashInTransaction(amount, selectedMethod, selectedCurrency, accountNameInput,
                    accountNumberInput);
            action.execute(currentUser);

            Transaction last = currentUser.getLastTransaction();
            double amountInPHP = action.convertToPHP(amount, selectedCurrency);
            String symbol = action.getCurrencySymbol(selectedCurrency);

            // Styled Receipt Dialog
            JDialog dialog = new JDialog(frame, "Cash In Receipt", true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(360, 340);
            dialog.setLayout(new BorderLayout());
            dialog.setLocationRelativeTo(frame);

            ImageIcon backgroundIcon = new ImageIcon("receipt.jpg");
            Image backgroundImage = backgroundIcon.getImage();

            JPanel receiptPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            };

            receiptPanel.setOpaque(false);
            receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
            receiptPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

            JLabel title = new JLabel("EABANK Cash-In Receipt");
            title.setFont(new Font("Segoe UI", Font.BOLD, 16));
            title.setForeground(new Color(0, 100, 0));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel line = new JLabel("-----------------------------");
            line.setFont(new Font("Monospaced", Font.PLAIN, 12));
            line.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel original = new JLabel("Original: " + symbol + String.format("%.2f", amount));
            original.setFont(new Font("Monospaced", Font.PLAIN, 12));
            original.setForeground(Color.DARK_GRAY);
            original.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add account name and masked account number if Credit/Debit Card
            if ("Credit/Debit Card".equals(selectedMethod)) {
                JLabel accountDetails = new JLabel("Account: " + accountNameInput + " (****"
                        + (accountNumberInput.length() >= 4
                                ? accountNumberInput.substring(accountNumberInput.length() - 4)
                                : accountNumberInput)
                        + ")*");
                accountDetails.setFont(new Font("Monospaced", Font.PLAIN, 12));
                accountDetails.setForeground(Color.DARK_GRAY);
                accountDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
                receiptPanel.add(accountDetails);
            }

            JLabel converted = new JLabel("Converted: ₱" + String.format("%.2f", amountInPHP));
            converted.setFont(new Font("Monospaced", Font.PLAIN, 12));
            converted.setForeground(Color.DARK_GRAY);
            converted.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel method = new JLabel("Method: " + selectedMethod);
            method.setFont(new Font("Monospaced", Font.PLAIN, 12));
            method.setForeground(Color.DARK_GRAY);
            method.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel ref = new JLabel("Ref No: " + last.getReferenceNumber());
            ref.setFont(new Font("Monospaced", Font.PLAIN, 12));
            ref.setForeground(Color.DARK_GRAY);
            ref.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel date = new JLabel("Date: " + last.getTimestamp());
            date.setFont(new Font("Monospaced", Font.PLAIN, 12));
            date.setForeground(Color.DARK_GRAY);
            date.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel status = new JLabel("Status: SUCCESSFUL");
            status.setFont(new Font("Segoe UI", Font.BOLD, 13));
            status.setForeground(new Color(0, 128, 0));
            status.setAlignmentX(Component.CENTER_ALIGNMENT);

            receiptPanel.add(title);
            receiptPanel.add(Box.createVerticalStrut(10));
            receiptPanel.add(line);
            receiptPanel.add(Box.createVerticalStrut(5));
            receiptPanel.add(original);
            receiptPanel.add(converted);
            receiptPanel.add(method);
            receiptPanel.add(ref);
            receiptPanel.add(date);
            receiptPanel.add(status);
            receiptPanel.add(Box.createVerticalStrut(10));
            receiptPanel.add(line);

            JButton okButton = new JButton("OK");
            okButton.setFocusPainted(false);
            okButton.setBackground(new Color(200, 255, 200));
            okButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
            okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            okButton.addActionListener(ev -> dialog.dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.add(okButton);

            dialog.add(receiptPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        });

        backButton.addActionListener(e -> showMainMenu());
    }

    private static void cashOut() {
        JPanel panel = new BackgroundPanel();
        panel.setLayout(null);

        JLabel heading4 = new JLabel("MKA", SwingConstants.CENTER);
        heading4.setFont(new Font("Arial Black", Font.BOLD, 30));
        heading4.setForeground(Color.WHITE);
        heading4.setBounds(186, 75, 100, 30);

        JLabel subheading4 = new JLabel("EABANK SYSTEM", SwingConstants.CENTER);
        subheading4.setFont(new Font("Arial", Font.BOLD, 22));
        subheading4.setForeground(Color.WHITE);
        subheading4.setBounds(140, 125, 200, 30);

        JLabel methodLabel = new JLabel("Select Outlet:");
        methodLabel.setForeground(Color.WHITE);
        String[] methods = { "EABANK Machine", "7-Eleven", "Uncle John's", "FamilyMart",
                "Cebuana Lhuillier", "Palawan Pawnshop", "BDO Bank", "BPI Bank", "Metrobank", "M.Lhuillier"
        };
        JComboBox<String> methodComboBox = new JComboBox<>(methods);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.WHITE);
        JTextField amountField = new JTextField();

        JLabel currencyLabel = new JLabel("Currency:");
        currencyLabel.setForeground(Color.WHITE);
        String[] currencies = { "PHP (₱)", "USD ($)", "JPY (¥)" };
        JComboBox<String> currencyComboBox = new JComboBox<>(currencies);

        JButton confirmButton = createStyledButton("Confirm");
        JButton backButton = createStyledButton("Back");

        int labelWidth = 130;
        int fieldWidth = 180;
        int baseX = 75;
        int baseY = 220;
        int gap = 60;

        methodLabel.setBounds(baseX, baseY, labelWidth, 25);
        methodComboBox.setBounds(baseX + labelWidth, baseY, fieldWidth, 25);

        amountLabel.setBounds(baseX, baseY + gap, labelWidth, 25);
        amountField.setBounds(baseX + labelWidth, baseY + gap, fieldWidth, 25);

        currencyLabel.setBounds(baseX, baseY + gap * 2, labelWidth, 25);
        currencyComboBox.setBounds(baseX + labelWidth, baseY + gap * 2, fieldWidth, 25);

        confirmButton.setBounds(baseX + 40, baseY + gap * 3 + 25, 120, 40);
        backButton.setBounds(baseX + 180, baseY + gap * 3 + 25, 120, 40);

        panel.add(heading4);
        panel.add(subheading4);
        panel.add(methodLabel);
        panel.add(methodComboBox);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(currencyLabel);
        panel.add(currencyComboBox);
        panel.add(confirmButton);
        panel.add(backButton);

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();

        confirmButton.addActionListener(e -> {
            String amtText = amountField.getText().trim();
            if (amtText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter an amount.", "Input Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(amtText);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Amount must be positive.", "Invalid Amount",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectedCurrency = (String) currencyComboBox.getSelectedItem();
            double amountInPHP = convertToPHP(amount, selectedCurrency);
            String selectedOutlet = (String) methodComboBox.getSelectedItem();

            if (currentUser.getBalance() < amountInPHP) {
                JOptionPane.showMessageDialog(frame, "Insufficient balance.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(frame,
                    String.format("You chose to cash out %s %.2f at %s. Confirm?", getCurrencySymbol(selectedCurrency),
                            amount, selectedOutlet),
                    "Confirm Cash Out", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                currentUser.cashOut(amountInPHP);
                Transaction lastTransaction = currentUser.getLastTransaction();

                // Show receipt in styled JDialog
                JDialog dialog = new JDialog(frame, "Cash Out Receipt", true);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setSize(360, 320);
                dialog.setLocationRelativeTo(frame);
                dialog.setLayout(new BorderLayout());

                ImageIcon bgIcon = new ImageIcon("receipt.jpg");
                Image bgImage = bgIcon.getImage();

                JPanel receiptPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                    }
                };

                receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
                receiptPanel.setOpaque(false);
                receiptPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

                JLabel title = new JLabel("EABANK Cash-Out Receipt");
                title.setFont(new Font("Segoe UI", Font.BOLD, 16));
                title.setForeground(new Color(139, 0, 0));
                title.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel amountLabelR = new JLabel("₱ " + String.format("%.2f", amountInPHP));
                amountLabelR.setFont(new Font("Segoe UI", Font.BOLD, 20));
                amountLabelR.setForeground(new Color(178, 34, 34));
                amountLabelR.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel pickup = new JLabel("Pick up at: " + selectedOutlet);
                JLabel ref = new JLabel("Ref No: " + lastTransaction.getReferenceNumber());
                JLabel date = new JLabel("Date: " + lastTransaction.getTimestamp());
                JLabel status = new JLabel("Status: SUCCESSFUL");

                for (JLabel lbl : new JLabel[] { pickup, ref, date }) {
                    lbl.setFont(new Font("Monospaced", Font.PLAIN, 12));
                    lbl.setForeground(Color.DARK_GRAY);
                    lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
                }

                status.setFont(new Font("Segoe UI", Font.BOLD, 13));
                status.setForeground(new Color(34, 139, 34));
                status.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel line = new JLabel("-----------------------------");
                line.setFont(new Font("Monospaced", Font.PLAIN, 12));
                line.setAlignmentX(Component.CENTER_ALIGNMENT);

                receiptPanel.add(title);
                receiptPanel.add(Box.createVerticalStrut(10));
                receiptPanel.add(line);
                receiptPanel.add(Box.createVerticalStrut(5));
                receiptPanel.add(amountLabelR);
                receiptPanel.add(Box.createVerticalStrut(10));
                receiptPanel.add(pickup);
                receiptPanel.add(ref);
                receiptPanel.add(date);
                receiptPanel.add(status);
                receiptPanel.add(Box.createVerticalStrut(10));
                receiptPanel.add(line);

                JButton okButton = new JButton("OK");
                okButton.setFocusPainted(false);
                okButton.setBackground(new Color(230, 230, 255));
                okButton.setForeground(Color.BLACK);
                okButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
                okButton.setPreferredSize(new Dimension(80, 30));
                okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                okButton.addActionListener(ev -> dialog.dispose());

                JPanel buttonPanel = new JPanel();
                buttonPanel.setBackground(Color.WHITE);
                buttonPanel.add(okButton);

                dialog.add(receiptPanel, BorderLayout.CENTER);
                dialog.add(buttonPanel, BorderLayout.SOUTH);
                dialog.setVisible(true);
            }
        });

        backButton.addActionListener(e -> showMainMenu());
    }

    private static User findUserByMobile(String mobile) {
        for (User user : users) {
            if (user.getMobile().equals(mobile)) {
                return user;
            }
        }
        return null;
    }

    private static double convertToPHP(double amount, String currency) {
        return switch (currency) {
            case "PHP (₱)" -> amount;
            case "USD ($)" -> amount * 55.5;
            case "JPY (¥)" -> amount * 0.48;
            default -> 0;
        };
    }

    private static String getCurrencySymbol(String currency) {
        return switch (currency) {
            case "PHP (₱)" -> "₱";
            case "USD ($)" -> "$";
            case "JPY (¥)" -> "¥";
            default -> "";
        };
    }

    private static void showMoneyExchanger() {
        JPanel panel = new BackgroundPanel();

        JLabel heading5 = new JLabel("MKA", SwingConstants.CENTER);
        heading5.setFont(new Font("Arial Black", Font.BOLD, 30));
        heading5.setForeground(Color.WHITE);
        heading5.setBounds(186, 75, 100, 30);

        JLabel subheading5 = new JLabel("EABANK SYSTEM", SwingConstants.CENTER);
        subheading5.setFont(new Font("Arial", Font.BOLD, 22));
        subheading5.setForeground(Color.WHITE);
        subheading5.setBounds(140, 125, 200, 30);

        JLabel amountLabel = new JLabel("Amount in PHP:");
        JTextField amountField = new JTextField();

        JLabel targetCurrencyLabel = new JLabel("Convert To:");
        String[] targetCurrencies = { "JPY (¥)", "USD ($)" };
        JComboBox<String> targetCurrencyComboBox = new JComboBox<>(targetCurrencies);

        JButton convertButton = createStyledButton("Convert");
        JButton backButton = createStyledButton("Back");

        int labelWidth = 120;
        int fieldWidth = 180;
        int baseX = 90;
        int baseY = 230;
        int verticalGap = 60;

        amountLabel.setBounds(baseX, baseY, labelWidth, 25);
        amountField.setBounds(baseX + labelWidth, baseY, fieldWidth, 25);

        targetCurrencyLabel.setBounds(baseX, baseY + verticalGap, labelWidth, 25);
        targetCurrencyComboBox.setBounds(baseX + labelWidth, baseY + verticalGap, fieldWidth, 25);

        convertButton.setBounds(baseX + 25, baseY + verticalGap * 2 + 7, 120, 40);
        backButton.setBounds(baseX + 160, baseY + verticalGap * 2 + 7, 120, 40);

        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(labelFont);
        targetCurrencyLabel.setForeground(Color.WHITE);
        targetCurrencyLabel.setFont(labelFont);

        panel.add(heading5);
        panel.add(subheading5);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(targetCurrencyLabel);
        panel.add(targetCurrencyComboBox);
        panel.add(convertButton);
        panel.add(backButton);

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();

        convertButton.addActionListener(e -> {
            String amtText = amountField.getText().trim();
            if (amtText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter an amount in PHP.", "Input Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(amtText);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Amount must be positive.", "Invalid Amount",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String targetCurrency = (String) targetCurrencyComboBox.getSelectedItem();
            double convertedAmount;
            String symbol;

            if ("JPY (¥)".equals(targetCurrency)) {
                convertedAmount = amount / 2.08;
                symbol = "¥";
            } else if ("USD ($)".equals(targetCurrency)) {
                convertedAmount = amount / 55.5;
                symbol = "$";
            } else {
                convertedAmount = amount;
                symbol = "";
            }

            // Show receipt
            JDialog dialog = new JDialog(frame, "Currency Conversion Receipt", true);
            dialog.setSize(360, 320);
            dialog.setLocationRelativeTo(frame);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLayout(new BorderLayout());

            ImageIcon bgIcon = new ImageIcon("receipt.jpg");
            Image bgImage = bgIcon.getImage();

            JPanel receiptPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            };

            receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
            receiptPanel.setOpaque(false);
            receiptPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

            JLabel title = new JLabel("EABANK Currency Receipt");
            title.setFont(new Font("Segoe UI", Font.BOLD, 16));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setForeground(new Color(0, 102, 204));

            JLabel line = new JLabel("-----------------------------");
            line.setFont(new Font("Monospaced", Font.PLAIN, 12));
            line.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel orig = new JLabel(String.format("PHP: ₱ %.2f", amount));
            orig.setFont(new Font("Monospaced", Font.PLAIN, 14));
            orig.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel result = new JLabel(String.format("Converted: %s %.2f", symbol, convertedAmount));
            result.setFont(new Font("Monospaced", Font.BOLD, 16));
            result.setForeground(new Color(34, 139, 34));
            result.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel conversionDate = new JLabel(
                    "Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            conversionDate.setFont(new Font("Monospaced", Font.PLAIN, 12));
            conversionDate.setForeground(Color.DARK_GRAY);
            conversionDate.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel status = new JLabel("Status: SUCCESSFUL");
            status.setFont(new Font("Segoe UI", Font.BOLD, 13));
            status.setForeground(new Color(34, 139, 34));
            status.setAlignmentX(Component.CENTER_ALIGNMENT);

            receiptPanel.add(title);
            receiptPanel.add(Box.createVerticalStrut(10));
            receiptPanel.add(line);
            receiptPanel.add(Box.createVerticalStrut(5));
            receiptPanel.add(orig);
            receiptPanel.add(Box.createVerticalStrut(5));
            receiptPanel.add(result);
            receiptPanel.add(Box.createVerticalStrut(5));
            receiptPanel.add(conversionDate);
            receiptPanel.add(status);
            receiptPanel.add(Box.createVerticalStrut(10));
            receiptPanel.add(line);

            JButton okButton = new JButton("OK");
            okButton.setFocusPainted(false);
            okButton.setBackground(new Color(230, 230, 255));
            okButton.setForeground(Color.BLACK);
            okButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
            okButton.setPreferredSize(new Dimension(80, 30));
            okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            okButton.addActionListener(ev -> dialog.dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.add(okButton);

            dialog.add(receiptPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.setVisible(true);
        });

        backButton.addActionListener(e -> showMainMenu());
    }

    private static void transfer() {
        String recipientMobile = JOptionPane.showInputDialog(frame, "Enter recipient mobile number:");
        if (recipientMobile != null) {
            recipientMobile = recipientMobile.trim();
            User recipient = findUserByMobile(recipientMobile);
            if (recipient != null) {
                if (recipient == currentUser) {
                    JOptionPane.showMessageDialog(frame, "Cannot transfer to your own account.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String input = JOptionPane.showInputDialog(frame, "Enter amount to transfer:");
                if (input != null) {
                    try {
                        double amount = Double.parseDouble(input);
                        if (amount > 0 && currentUser.getBalance() >= amount) {
                            currentUser.transfer(recipient, amount);
                            Transaction lastTransaction = currentUser.getLastTransaction();

                            // Receipt dialog
                            JDialog dialog = new JDialog(frame, "Transfer Receipt", true);
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            dialog.setSize(360, 320);
                            dialog.setLayout(new BorderLayout());
                            dialog.setLocationRelativeTo(frame);

                            ImageIcon bgIcon = new ImageIcon("receipt.jpg");
                            Image bgImage = bgIcon.getImage();

                            JPanel receiptPanel = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    super.paintComponent(g);
                                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                                }
                            };
                            receiptPanel.setOpaque(false);
                            receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
                            receiptPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

                            JLabel title = new JLabel("EABANK Transfer Receipt");
                            title.setFont(new Font("Segoe UI", Font.BOLD, 16));
                            title.setAlignmentX(Component.CENTER_ALIGNMENT);
                            title.setForeground(new Color(0, 102, 204));

                            JLabel line = new JLabel("-----------------------------");
                            line.setFont(new Font("Monospaced", Font.PLAIN, 12));
                            line.setAlignmentX(Component.CENTER_ALIGNMENT);

                            JLabel amountLabelR = new JLabel("₱ " + String.format("%.2f", amount));
                            amountLabelR.setFont(new Font("Segoe UI", Font.BOLD, 20));
                            amountLabelR.setForeground(new Color(34, 139, 34));
                            amountLabelR.setAlignmentX(Component.CENTER_ALIGNMENT);

                            JLabel toUser = new JLabel("Sent to: " + recipient.getName());
                            toUser.setFont(new Font("Monospaced", Font.PLAIN, 12));
                            toUser.setForeground(Color.DARK_GRAY);
                            toUser.setAlignmentX(Component.CENTER_ALIGNMENT);

                            JLabel ref = new JLabel("Ref No: " + lastTransaction.getReferenceNumber());
                            ref.setFont(new Font("Monospaced", Font.PLAIN, 12));
                            ref.setForeground(Color.DARK_GRAY);
                            ref.setAlignmentX(Component.CENTER_ALIGNMENT);

                            JLabel date = new JLabel("Date: " + lastTransaction.getTimestamp());
                            date.setFont(new Font("Monospaced", Font.PLAIN, 12));
                            date.setForeground(Color.DARK_GRAY);
                            date.setAlignmentX(Component.CENTER_ALIGNMENT);

                            JLabel status = new JLabel("Status: SUCCESSFUL");
                            status.setFont(new Font("Segoe UI", Font.BOLD, 13));
                            status.setForeground(new Color(0, 128, 0));
                            status.setAlignmentX(Component.CENTER_ALIGNMENT);

                            receiptPanel.add(title);
                            receiptPanel.add(Box.createVerticalStrut(10));
                            receiptPanel.add(line);
                            receiptPanel.add(Box.createVerticalStrut(5));
                            receiptPanel.add(amountLabelR);
                            receiptPanel.add(Box.createVerticalStrut(10));
                            receiptPanel.add(toUser);
                            receiptPanel.add(ref);
                            receiptPanel.add(date);
                            receiptPanel.add(status);
                            receiptPanel.add(Box.createVerticalStrut(10));
                            receiptPanel.add(line);

                            JButton okButton = new JButton("OK");
                            okButton.setFocusPainted(false);
                            okButton.setBackground(new Color(230, 230, 255));
                            okButton.setForeground(Color.BLACK);
                            okButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
                            okButton.setPreferredSize(new Dimension(80, 30));
                            okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            okButton.addActionListener(ev -> dialog.dispose());

                            JPanel buttonPanel = new JPanel();
                            buttonPanel.setBackground(Color.WHITE);
                            buttonPanel.add(okButton);

                            dialog.add(receiptPanel, BorderLayout.CENTER);
                            dialog.add(buttonPanel, BorderLayout.SOUTH);
                            dialog.setVisible(true);

                        } else {
                            JOptionPane.showMessageDialog(frame, "Insufficient balance or invalid amount.", "Error",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount.", "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Recipient not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void checkBalance() {
        Transaction dummyTransaction = new Transaction("CHECK BALANCE", 0); // For timestamp only

        JDialog dialog = new JDialog(frame, "Balance Receipt", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(360, 300);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(frame);

        ImageIcon bgIcon = new ImageIcon("receipt.jpg");
        Image bgImage = bgIcon.getImage();

        JPanel receiptPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        receiptPanel.setOpaque(false);
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
        receiptPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        JLabel title = new JLabel("EABANK Balance Receipt");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(0, 102, 204));

        JLabel line = new JLabel("-----------------------------");
        line.setFont(new Font("Monospaced", Font.PLAIN, 12));
        line.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel amountLabelR = new JLabel("₱ " + String.format("%.2f", currentUser.getBalance()));
        amountLabelR.setFont(new Font("Segoe UI", Font.BOLD, 20));
        amountLabelR.setForeground(new Color(0, 128, 0));
        amountLabelR.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userLabel = new JLabel("Account: " + currentUser.getName());
        userLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        userLabel.setForeground(Color.DARK_GRAY);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel date = new JLabel("Date: " + dummyTransaction.getTimestamp());
        date.setFont(new Font("Monospaced", Font.PLAIN, 12));
        date.setForeground(Color.DARK_GRAY);
        date.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel status = new JLabel("Status: CHECKED");
        status.setFont(new Font("Segoe UI", Font.BOLD, 13));
        status.setForeground(new Color(0, 128, 128));
        status.setAlignmentX(Component.CENTER_ALIGNMENT);

        receiptPanel.add(title);
        receiptPanel.add(Box.createVerticalStrut(10));
        receiptPanel.add(line);
        receiptPanel.add(Box.createVerticalStrut(5));
        receiptPanel.add(amountLabelR);
        receiptPanel.add(Box.createVerticalStrut(10));
        receiptPanel.add(userLabel);
        receiptPanel.add(date);
        receiptPanel.add(status);
        receiptPanel.add(Box.createVerticalStrut(10));
        receiptPanel.add(line);

        JButton okButton = new JButton("OK");
        okButton.setFocusPainted(false);
        okButton.setBackground(new Color(230, 230, 255));
        okButton.setForeground(Color.BLACK);
        okButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        okButton.setPreferredSize(new Dimension(80, 30));
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.addActionListener(ev -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);

        dialog.add(receiptPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private static void viewTransactionHistory() {
        ArrayList<Transaction> history = currentUser.getTransactionHistory();
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No transactions yet.", "Transaction History",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Transaction transaction : history) {
                sb.append(transaction.toString()).append("\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(450, 300));
            JOptionPane.showMessageDialog(frame, scrollPane, "Transaction History", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Transaction Class
    static class Transaction {
        private final String referenceNumber;
        private final String type;
        private final double amount;
        private final LocalDateTime timestamp;

        public Transaction(String type, double amount) {
            this.referenceNumber = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            this.type = type;
            this.amount = amount;
            this.timestamp = LocalDateTime.now();
        }

        public String getReferenceNumber() {
            return referenceNumber;
        }

        public String getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }

        public String getTimestamp() {
            return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        @Override
        public String toString() {
            return String.format("%-15s ₱%10.2f Ref:%s Date:%s", type, amount, referenceNumber, getTimestamp());
        }
    }

    // User Class
    static class User {
        private final String name;
        private final String mobile;
        private final String hashedPin;
        private double balance;
        private final ArrayList<Transaction> transactionHistory;

        public User(String name, String mobile, String pin) {
            this.name = name;
            this.mobile = mobile;
            this.hashedPin = hashPin(pin);
            this.balance = 0.0;
            this.transactionHistory = new ArrayList<>();
        }

        private String hashPin(String pin) {
            return Integer.toHexString(pin.hashCode());
        }

        public boolean checkPin(String pin) {
            return this.hashedPin.equals(hashPin(pin));
        }

        public String getName() {
            return name;
        }

        public String getMobile() {
            return mobile;
        }

        public double getBalance() {
            return balance;
        }

        public void cashIn(double amount) {
            balance += amount;
            transactionHistory.add(new Transaction("Cash In", amount));
        }

        public void cashOut(double amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Cash Out", amount));
        }

        public void transfer(User recipient, double amount) {
            this.balance -= amount;
            recipient.balance += amount;
            transactionHistory.add(new Transaction("Transfer", amount));
            recipient.transactionHistory.add(new Transaction("Received", amount));
        }

        public ArrayList<Transaction> getTransactionHistory() {
            return transactionHistory;
        }

        public Transaction getLastTransaction() {
            if (transactionHistory.isEmpty())
                return null;
            return transactionHistory.get(transactionHistory.size() - 1);
        }
    }
}

abstract class TransactionAction {
    protected double amount;

    public TransactionAction(double amount) {
        this.amount = amount;
    }

    public abstract void execute(EABANKSYSTEM.User user);

    public abstract String generateReceipt(EABANKSYSTEM.Transaction transaction);
}

class CashInTransaction extends TransactionAction {
    private String method;
    private String currency;
    private String accountName;
    private String accountNumber;

    public CashInTransaction(double amount, String method, String currency, String accountName, String accountNumber) {
        super(amount);
        this.method = method;
        this.currency = currency;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
    }

    @Override
    public void execute(EABANKSYSTEM.User user) {
        double convertedAmount = convertToPHP(amount, currency);
        user.cashIn(convertedAmount);
    }

    @Override
    public String generateReceipt(EABANKSYSTEM.Transaction transaction) {
        return String.format(
                "--- CASH IN RECEIPT ---%nMethod: %s%nOriginal Amount: %s %.2f%nConverted to PHP: ₱ %.2f%nAccount: %s (****%s)*%nRef No: %s%nDate: %s%nStatus: SUCCESSFUL%n-------------------------%n",
                method, getCurrencySymbol(currency), amount,
                convertToPHP(amount, currency),
                accountName, accountNumber.substring(accountNumber.length() - 4),
                transaction.getReferenceNumber(),
                transaction.getTimestamp());
    }

    public double convertToPHP(double amount, String currency) {
        return switch (currency) {
            case "PHP (₱)" -> amount;
            case "USD ($)" -> amount * 55.5;
            case "JPY (¥)" -> amount * 0.48;
            default -> 0;
        };
    }

    public String getCurrencySymbol(String currency) {
        return switch (currency) {
            case "PHP (₱)" -> "₱";
            case "USD ($)" -> "$";
            case "JPY (¥)" -> "¥";
            default -> "";
        };
    }
}