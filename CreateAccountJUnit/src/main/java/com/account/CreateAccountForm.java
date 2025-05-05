package com.account;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

public class CreateAccountForm {
    private static final Map<JTextField, String> placeholderMap = new HashMap<>();
    private static final Map<JTextField, JLabel> iconMap = new HashMap<>();
    private static final Color placeholderColor = Color.GRAY;
    private static final Color normalTextColor = Color.BLACK;
    private static final Font placeholderFont = new Font("SansSerif", Font.PLAIN, 16);
    private static final Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);
    private static JLabel passwordStrengthLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Create New Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);

        JPanel container = new JPanel(new CardLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 12, 6, 12);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        JLabel title = new JLabel("Create New Account");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        formPanel.add(title, gbc);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Border defaultBorder = new JTextField().getBorder();

        gbc.gridy++;
        JTextField fnField = addLabeledField(formPanel, gbc, "First Name:", "Enter your first name");
        JLabel fnError = addErrorLabel(formPanel, gbc);

        gbc.gridy++;
        JTextField lnField = addLabeledField(formPanel, gbc, "Last Name:", "Enter your last name");
        JLabel lnError = addErrorLabel(formPanel, gbc);

        gbc.gridy++;
        JTextField emailField = addLabeledField(formPanel, gbc, "E-mail:", "example@example.com");
        JLabel emailError = addErrorLabel(formPanel, gbc);

        gbc.gridy++;
        JPasswordField pwField = (JPasswordField) addLabeledField(formPanel, gbc, "Password:", "Enter a strong password");
        JLabel pwError = addErrorLabel(formPanel, gbc);

        gbc.gridy++;
        passwordStrengthLabel = new JLabel(" ");
        passwordStrengthLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        formPanel.add(passwordStrengthLabel, gbc);

        gbc.gridy++;
        JPasswordField cpwField = (JPasswordField) addLabeledField(formPanel, gbc, "Confirm Password:", "Re-enter your password");
        JLabel cpwError = addErrorLabel(formPanel, gbc);

        gbc.gridy++;
        JTextField dobField = addLabeledField(formPanel, gbc, "Date of Birth:", "dd/mm/yyyy");
        JLabel dobError = addErrorLabel(formPanel, gbc);

        gbc.gridy++;
        JButton submitBtn = new JButton("SUBMIT");
        submitBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitBtn.setBackground(new Color(59, 89, 182));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(submitBtn, gbc);
        gbc.gridwidth = 1;

        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitBtn.setBackground(new Color(30, 70, 200));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                submitBtn.setBackground(new Color(59, 89, 182));
            }
        });

        JPanel successPanel = new JPanel();
        successPanel.setBackground(new Color(0, 153, 76));
        successPanel.setLayout(new GridBagLayout());

        JLabel successLabel = new JLabel("✅ Account Created Successfully!");
        successLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        successLabel.setForeground(Color.WHITE);
        successPanel.add(successLabel);

        container.add(formPanel, "form");
        container.add(successPanel, "success");

        submitBtn.addActionListener(e -> {
            boolean hasError = false;

            String fn = getRealText(fnField);
            String ln = getRealText(lnField);
            String email = getRealText(emailField);
            String pw = getRealText(pwField);
            String cpw = getRealText(cpwField);
            String dob = getRealText(dobField);

            if (!getFirstNameError(fn).isEmpty()) {
                setError(fnField, fnError, getFirstNameError(fn));
                hasError = true;
            } else {
                setSuccess(fnField, fnError);
            }

            if (!getLastNameError(ln).isEmpty()) {
                setError(lnField, lnError, getLastNameError(ln));
                hasError = true;
            } else {
                setSuccess(lnField, lnError);
            }

            if (!getEmailError(email).isEmpty()) {
                setError(emailField, emailError, getEmailError(email));
                hasError = true;
            } else {
                setSuccess(emailField, emailError);
            }

            if (!Validator.getPasswordErrorMessage(pw).isEmpty()) {
                setError(pwField, pwError, Validator.getPasswordErrorMessage(pw));
                hasError = true;
            } else {
                setSuccess(pwField, pwError);
            }

            if (cpw.isEmpty()) {
    setError(cpwField, cpwError, "⚠ Confirm Password cannot be empty.");
    hasError = true;
} else if (!Validator.passwordsMatch(pw, cpw)) {
    setError(cpwField, cpwError, "⚠ Passwords do not match.");
    hasError = true;
} else {
    setSuccess(cpwField, cpwError);
}

            if (!getDOBError(dob).isEmpty()) {
                setError(dobField, dobError, getDOBError(dob));
                hasError = true;
            } else {
                setSuccess(dobField, dobError);
            }

            if (!hasError) {
                submitBtn.setText("Loading...");
                submitBtn.setEnabled(false);
                Timer timer = new Timer(1500, evt -> {
                    CardLayout cl = (CardLayout) container.getLayout();
                    cl.show(container, "success");
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        addRealtimeValidation(fnField, fnError, CreateAccountForm::getFirstNameError);
        addRealtimeValidation(lnField, lnError, CreateAccountForm::getLastNameError);
        addRealtimeValidation(emailField, emailError, CreateAccountForm::getEmailError);
        addRealtimeValidation(dobField, dobError, CreateAccountForm::getDOBError);
        addRealtimeValidation(pwField, pwError, text -> {
            updatePasswordStrength(text);
            return Validator.getPasswordErrorMessage(text);
        });
        addRealtimeValidation(cpwField, cpwError, text -> {
    String pw = getRealText(pwField);
    if (text.isEmpty()) return "⚠ Confirm Password cannot be empty.";
    if (!Validator.passwordsMatch(pw, text)) return "⚠ Passwords do not match.";
    return "";
});

        frame.setContentPane(container);
        frame.setVisible(true);
    }

    private static String getRealText(JTextField field) {
        String text = (field instanceof JPasswordField)
                ? new String(((JPasswordField) field).getPassword()).trim()
                : field.getText().trim();
        return text.equals(placeholderMap.get(field)) ? "" : text;
    }

    private static void setError(JTextField field, JLabel errorLabel, String message) {
        field.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        errorLabel.setText(message);
        iconMap.get(field).setText("⚠️");
    }

    private static void setSuccess(JTextField field, JLabel errorLabel) {
        field.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 76), 2));
        iconMap.get(field).setText("✅");
        errorLabel.setText(" ");
    }

    private static JTextField addLabeledField(JPanel panel, GridBagConstraints gbc, String labelText, String placeholder) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        panel.add(label, gbc);

        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBackground(Color.WHITE);

        JTextField field = labelText.toLowerCase().contains("password") ? new JPasswordField(25) : new JTextField(25);
        field.setFont(placeholderFont);
        field.setForeground(placeholderColor);
        field.setText(placeholder);

        placeholderMap.put(field, placeholder);

        JLabel icon = new JLabel();
        icon.setFont(new Font("SansSerif", Font.PLAIN, 16));
        fieldPanel.add(field, BorderLayout.CENTER);
        fieldPanel.add(icon, BorderLayout.EAST);
        iconMap.put(field, icon);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setFont(fieldFont);
                    field.setForeground(normalTextColor);
                }
            }
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setFont(placeholderFont);
                    field.setForeground(placeholderColor);
                }
            }
        });

        gbc.gridx = 1;
        panel.add(fieldPanel, gbc);
        return field;
    }

    private static JLabel addErrorLabel(JPanel panel, GridBagConstraints gbc) {
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridy++;
        gbc.gridx = 1;
        panel.add(errorLabel, gbc);
        return errorLabel;
    }

    private static void addRealtimeValidation(JTextField field, JLabel errorLabel, java.util.function.Function<String, String> validationFunction) {
        field.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String value = getRealText(field);
                String errorMessage = validationFunction.apply(value);
                if (errorMessage.isEmpty()) {
                    setSuccess(field, errorLabel);
                } else {
                    setError(field, errorLabel, errorMessage);
                }
            }
        });
    }

    private static void updatePasswordStrength(String password) {
        if (password.isEmpty()) {
            passwordStrengthLabel.setText(" ");
            return;
        }
        int score = 0;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[!@#$%^&*()_\\-+=.].*")) score++;
        if (password.length() >= 8) score++;

        if (score <= 2) {
            passwordStrengthLabel.setText("Password Strength: Weak");
            passwordStrengthLabel.setForeground(Color.RED);
        } else if (score <= 4) {
            passwordStrengthLabel.setText("Password Strength: Medium");
            passwordStrengthLabel.setForeground(Color.ORANGE);
        } else {
            passwordStrengthLabel.setText("Password Strength: Strong");
            passwordStrengthLabel.setForeground(new Color(0, 153, 76));
        }
    }

   

    private static String getFirstNameError(String value) {
        if (value.isEmpty()) return "⚠ First name cannot be empty.";
        if (value.length() < 2) return "⚠ First name must be at least 2 letters.";
        return "";
    }

    private static String getLastNameError(String value) {
        if (value.isEmpty()) return "⚠ Last name cannot be empty.";
        if (value.length() < 2) return "⚠ Last name must be at least 2 letters.";
        return "";
    }

    private static String getEmailError(String value) {
        if (value.isEmpty()) return "⚠ Email cannot be empty.";
        if (!Validator.isValidEmail(value)) return "⚠ Invalid email format.";
        return "";
    }

    private static String getDOBError(String value) {
        if (value.isEmpty()) return "⚠ Date of Birth cannot be empty.";
        if (!Validator.isValidDOB(value)) return "⚠ Invalid DOB format.";
        return "";
    }
}
