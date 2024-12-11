import db.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class itemDetails {
    private JLabel headerLabel;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField locationField;
    private JTextField dateFoundField;
    private JTextField timeFoundField;
    private JTextField descriptionField;
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JLabel nameLabel;
    private JLabel categoryLabel;
    private JLabel locationLabel;
    private JLabel dateFoundLabel;
    private JLabel timeFoundLabel;
    private JLabel descriptionLabel;
    private JLabel subHeaderLabel;
    private JTextField reporterField;
    private JTextField emailField;
    private JTextField phoneField;
    private JLabel reporterLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JPanel actionsPanel;

    public itemDetails(classDTO item, int userId) {
        nameField.setText(item.getItemName());
        categoryField.setText(item.getCategory());
        locationField.setText(item.getLocationFound());
        dateFoundField.setText(item.getDateFound());
        timeFoundField.setText(item.getTimeFound());
        descriptionField.setText(item.getDescription());
        reporterField.setText(item.getReporter());
        emailField.setText(item.getEmail());
        phoneField.setText(item.getPhone());

        String userRole = UserDB.getUserRoleById(userId); // to get user role

        if ("staff".equals(userRole) || "admin".equals(userRole) || (item.getReporterId() == userId && !"claimed".equals(item.getStatus()))) {
            deleteBtn.setEnabled(true);
            updateBtn.setEnabled(true);
        } else {
            deleteBtn.setEnabled(false);
            updateBtn.setEnabled(false);
        }

        boolean isEditable = "staff".equals(userRole) || "admin".equals(userRole) || (item.getReporterId() == userId && !"claimed".equals(item.getStatus()));
        nameField.setEditable(isEditable);
        categoryField.setEditable(isEditable);
        locationField.setEditable(isEditable);
        dateFoundField.setEditable(isEditable);
        timeFoundField.setEditable(isEditable);
        descriptionField.setEditable(isEditable);

        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!deleteBtn.isEnabled()) {
                    JOptionPane.showMessageDialog(null, "You do not have permission to delete this item.", "Access Denied", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int itemId = item.getItemId();
                boolean success = ItemDB.deleteItem(itemId);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Item deleted successfully.");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                    if (frame != null) {
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete item.");
                }
            }
        });

        updateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!updateBtn.isEnabled()) {
                    JOptionPane.showMessageDialog(null, "You do not have permission to update this item.", "Access Denied", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int itemId = item.getItemId();
                String name = nameField.getText();
                String category = categoryField.getText();
                String location = locationField.getText();
                String dateFound = dateFoundField.getText();
                String timeFound = timeFoundField.getText();
                String description = descriptionField.getText();

                boolean success = ItemDB.updateItemDetails(itemId, name, category, location, dateFound, timeFound, description);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Item details updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update item details.");
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
