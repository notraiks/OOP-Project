import db.DatabaseUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {
    private JPanel mainPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JPanel bodyPanel;
    private JPanel headerPanel;
    private JPanel containerPanel;
    private JPanel actionsPanel;
    private JLabel titleLabel;
    private JButton signupBtn;
    private JLabel emailLabel;
    private JLabel passwordLabel;

    private String userRole;

    public Login() {
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLoginAction();
            }
        });

        signupBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openSignUpWindow();
            }
        });
    }

    private void handleLoginAction() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both email and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userId = DatabaseUtil.validateLogin(email, password);
        String userRole = DatabaseUtil.getUserRoleById(userId);

        if (userId != -1) { // Successful login
            openHomeWindow(userId, userRole);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openSignUpWindow() {
        JFrame signupFrame = new JFrame("Sign Up");
        SignUp signup = new SignUp();
        signupFrame.setContentPane(signup.getMainPanel());
        signupFrame.setSize(600, 400);
        signupFrame.setLocationRelativeTo(null);
        signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signupFrame.setVisible(true);
    }

    private void openHomeWindow(int userId, String userRole) {
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        if (currentFrame != null) {
            currentFrame.dispose(); // Close login window
        }

        JFrame homeFrame = new JFrame("Home");
        Home home = new Home(userId, userRole);
        homeFrame.setContentPane(home.getMainPanel());
        homeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
