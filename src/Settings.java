import com.formdev.flatlaf.FlatLightLaf;

import db.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Settings {
    private JPanel mainPanel;
    private JTextField fNameField;
    private JTextField lNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JPasswordField confirmPassField;
    private JButton updateBtn;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JLabel titlePanel;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel passwordLabel;
    private JLabel confirmPassLabel;
    private JPanel actionPanel;
    private JTextField roleField;
    private JLabel roleLabel;

    private int userId;

    public Settings(int userId) {
        this.userId = userId;

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        populateUserDetails();

        updateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateUserDetails();
            }
        });
    }

    private void populateUserDetails() {
        classDTO userDetails = UserDB.getUser(userId);
        if (userDetails != null) {
            fNameField.setText(userDetails.getFirstName());
            lNameField.setText(userDetails.getLastName());
            emailField.setText(userDetails.getEmail());
            phoneField.setText(userDetails.getPhone());
            roleField.setText(UserDB.getUserRoleById(userId));
        } else {
            JOptionPane.showMessageDialog(null, "Error loading user details. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUserDetails() {
        String fName = fNameField.getText().trim();
        String lName = lNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String newPassword = new String(passwordField.getPassword()).trim();
        String confirmPassword = new String(confirmPassField.getPassword()).trim();

        if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPassword.isEmpty() && !newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = UserDB.updateUser(userId, fName, lName, email, phone, newPassword);
        if (success) {
            JOptionPane.showMessageDialog(null, "User details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error updating user details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
