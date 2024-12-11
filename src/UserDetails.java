import com.formdev.flatlaf.FlatLightLaf;

import db.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserDetails {
    private JPanel mainPanel;
    private JTextField fNameField;
    private JTextField lNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JPasswordField confirmPassField;
    private JComboBox<String> roleField;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel passwordLabel;
    private JLabel confirmPassLabel;
    private JLabel roleLabel;
    private JPanel actionsPanel;
    private JPanel bodyPanel;
    private JPanel headerPanel;
    private JLabel titleLabel;

    private int userId;

    public UserDetails(int userId) {
        this.userId = userId;

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        initializeRoleField();
        populateUserDetails();

        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteUser();
            }
        });

        updateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateUserDetails();
            }
        });
    }

    private void initializeRoleField() {
        roleField.addItem("admin");
        roleField.addItem("student");
        roleField.addItem("staff");
    }

    private void populateUserDetails() {
        classDTO userDetails = UserDB.getUser(userId);
        if (userDetails != null) {
            fNameField.setText(userDetails.getFirstName());
            lNameField.setText(userDetails.getLastName());
            emailField.setText(userDetails.getEmail());
            phoneField.setText(userDetails.getPhone());
            roleField.setSelectedItem(UserDB.getUserRoleById(userId));
        } else {
            JOptionPane.showMessageDialog(null, "Error loading user details. Please try again later.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUserDetails() {
        String fName = fNameField.getText().trim();
        String lName = lNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String newPassword = new String(passwordField.getPassword()).trim();
        String confirmPassword = new String(confirmPassField.getPassword()).trim();
        String role = ((String) roleField.getSelectedItem()).trim();

        if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPassword.isEmpty() && !newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!role.equals("admin") && !role.equals("student") && !role.equals("staff")) {
            JOptionPane.showMessageDialog(null, "Invalid role selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean success = UserDB.updateUserWithRole(userId, fName, lName, email, phone, newPassword, role);
        if (success) {
            JOptionPane.showMessageDialog(null, "User details updated successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error updating user details. Please try again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUser() {
        int confirmation = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this user?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = UserDB.deleteUser(userId);
            if (success) {
                JOptionPane.showMessageDialog(null, "User deleted successfully.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.getWindowAncestor(mainPanel).dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error deleting user. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}