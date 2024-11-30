import com.formdev.flatlaf.FlatLightLaf;
import com.raven.datechooser.DateChooser; // Library is acquired from Raven (https://github.com/DJ-Raven/datechooser)

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Report {
    private JPanel mainPanel;
    private JTextField nameField;
    private JComboBox<String> categoryField;
    private JTextField locationField;
    private JTextField dateFoundField;
    private JSpinner timeFoundField;
    private JTextField descriptionField;
    private JPanel headerPanel;
    private JLabel headerLabel;
    private JPanel bodyPanel;
    private JLabel nameLabel;
    private JLabel categoryLabel;
    private JLabel locationLabel;
    private JLabel dateFoundLabel;
    private JLabel timeFoundLabel;
    private JLabel descriptionLabel;
    private JButton cancelBtn;
    private JButton submitBtn;
    private JPanel actionsPanel;
    private DateChooser dateChooser; // Panel for the Calendar

    private int userId; // To store the logged-in user's ID

    public Report(int userId) {
        this.userId = userId; // Store the logged-in user's ID

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        initializeCategoryField();
        initializeDateAndTimeFields();

        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleSubmitAction();
            }
        });
    }

    private void initializeCategoryField() {
        categoryField.addItem("Accessories");
        categoryField.addItem("Bags & Backpacks");
        categoryField.addItem("Clothing");
        categoryField.addItem("Electronics");
        categoryField.addItem("Eyewear");
        categoryField.addItem("Footwear");
        categoryField.addItem("IDs & Cards");
        categoryField.addItem("Keys");
        categoryField.addItem("Mobile Devices");
        categoryField.addItem("Papers & Documents");
        categoryField.addItem("Tumblers");
        categoryField.addItem("Wallets & Purses");
        categoryField.addItem("Others");
    }

    private void initializeDateAndTimeFields() {
        // Initialize DateChooser for dateFoundField
        dateChooser = new DateChooser();
        dateChooser.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); // Required date format
        dateChooser.setTextField(dateFoundField); // Link the text field component to DateChooser

        // Initialize the time with JSpinner component
        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR);
        timeFoundField.setModel(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeFoundField, "HH:mm:ss");
        timeFoundField.setEditor(timeEditor);
    }

    private void handleSubmitAction() {
        String name = nameField.getText().trim();
        String category = (String) categoryField.getSelectedItem();
        String location = locationField.getText().trim();
        String dateFound = dateFoundField.getText().trim();
        Date timeFound = (Date) timeFoundField.getValue();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String timeFoundString = timeFormat.format(timeFound);
        String description = descriptionField.getText().trim();

        if (name.isEmpty() || location.isEmpty() || dateFound.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = db.DatabaseUtil.createItem(
                name, category, location, dateFound, timeFoundString, description, userId); // Include userId

        if (success) {
            JOptionPane.showMessageDialog(null, "Report submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to submit the report. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        // Reset all fields
        nameField.setText("");
        categoryField.setSelectedIndex(0);
        locationField.setText("");
        dateFoundField.setText("");
        timeFoundField.setValue(new Date());
        descriptionField.setText("");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}