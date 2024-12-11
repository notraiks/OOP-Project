import db.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClaimDetails {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JTextField requestField;
    private JTextField nameField;
    private JTextField claimerField;
    private JTextField dateField;
    private JTextField statusField;
    private JButton approveBtn;
    private JPanel actionsPanel;
    private JLabel requestIDLabel;
    private JLabel nameLabel;
    private JLabel claimerLabel;
    private JLabel dateLabel;
    private JLabel statusLabel;

    private int requestId;

    public ClaimDetails(int requestId, String itemName, String claimer, String requestDate, String status) {
        this.requestId = requestId;

        requestField.setText(String.valueOf(requestId));
        nameField.setText(itemName);
        claimerField.setText(claimer);
        dateField.setText(requestDate);
        statusField.setText(status);

        approveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                approveClaimRequest();
            }
        });
    }

    private void approveClaimRequest() {
        boolean success = ClaimDB.approveClaimRequest(requestId);
        if (success) {
            JOptionPane.showMessageDialog(null, "Claim request approved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            statusField.setText("Approved");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to approve claim request.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
