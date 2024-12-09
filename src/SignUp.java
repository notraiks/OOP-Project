import db.DatabaseUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUp {
    private JTextField fNameField;
    private JTextField lNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JButton signUpBtn;
    private JLabel lNameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel passwordLabel;
    private JPanel actionPanel;
    private JPanel bodyPanel;
    private JPanel headerPanel;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel fNameLabel;

    public SignUp() {
        signUpBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleSignupAction();
            }
        });
    }

    private void handleSignupAction() {
        // Retrieve inputs
        String fName = fNameField.getText().trim();
        String lName = lNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Validate inputs
        if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (phone.length() < 11 || !phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid phone number!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean isCreated = DatabaseUtil.createUser(fName, lName, email, phone, password);

        if (isCreated) {
            JOptionPane.showMessageDialog(null, "User created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFormFields();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to create user. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFormFields() {
        fNameField.setText("");
        lNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
