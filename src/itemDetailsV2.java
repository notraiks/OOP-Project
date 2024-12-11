import javax.swing.*;
import db.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class itemDetailsV2 {
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField dateFoundField;
    private JTextField timeFoundField;
    private JTextField reporterField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPanel headerPanel;
    private JLabel headerLabel;
    private JPanel bodyPanel;
    private JTextField locationField;
    private JTextField descriptionField;
    private JLabel nameLabel;
    private JLabel categoryLabel;
    private JLabel locationLabel;
    private JLabel dateFoundLabel;
    private JLabel timeFoundLabel;
    private JLabel descriptionLabel;
    private JLabel subHeaderLabel;
    private JLabel reporterLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JButton claimBtn;

    private int userId;

    public itemDetailsV2(classDTO item, int userId) {
        this.userId = userId;

        nameField.setText(item.getItemName());
        categoryField.setText(item.getCategory());
        locationField.setText(item.getLocationFound());
        dateFoundField.setText(item.getDateFound());
        timeFoundField.setText(item.getTimeFound());
        descriptionField.setText(item.getDescription());
        reporterField.setText(item.getReporter());
        emailField.setText(item.getEmail());
        phoneField.setText(item.getPhone());


        claimBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int itemId = item.getItemId();

                // Update the item status to "claimed" in the database
                boolean success = ClaimDB.createClaimRequest(itemId, userId);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Claim request submitted successfully.");
                    claimBtn.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to submit claim request.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }
}
