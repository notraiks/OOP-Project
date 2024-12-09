import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import db.*;

public class Home {
    private JPanel mainPanel;
    private JButton homeBtn;
    private JPanel headerPanel;
    private JPanel navPanel;
    private JButton reportBtn;
    private JButton historyBtn;
    private JButton settingsBtn;
    private JButton logoutBtn;
    private JPanel bodyPanel;
    private JTextField searchField;
    private JComboBox<String> categoryFilter;
    private JLabel searchLabel;
    private JLabel filterLabel;
    private JLabel header2;
    private JPanel containerPanel;
    private JPanel searchAndFilterPanel;
    private JTable table1;
    private JScrollPane tableScrollPane;
    private JButton claimHistoryBtn;

    private int userId;

    public Home(int userId, String userRole) {
        this.userId = userId;

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Initialize table
        String[] columns = {"ID", "Item Name", "Category", "Location Found", "Date Found", "Time Found", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Set the table model and hide the item_id column
        table1.setModel(tableModel);
        table1.removeColumn(table1.getColumnModel().getColumn(0)); // Hide the ID column
        DatabaseUtil.populate(tableModel, userId, "unclaimed", null);

        categoryFilter();;
        setupListeners(tableModel);

        reportBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame reportFrame = new JFrame("Report");
                Report report = new Report(userId); // Pass the userId to the Report class
                reportFrame.setContentPane(report.getMainPanel());
                reportFrame.setSize(600, 400);
                reportFrame.setLocationRelativeTo(null);
                reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                reportFrame.setVisible(true);
            }
        });

        settingsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    JFrame settingsFrame = new JFrame("Settings");
                    Settings settings = new Settings(userId); // Pass the userId to the Report class
                    settingsFrame.setContentPane(settings.getMainPanel());
                    settingsFrame.setSize(600, 400);
                    settingsFrame.setLocationRelativeTo(null);
                    settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    settingsFrame.setVisible(true);
            }
        });
        logoutBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // close the current Home page window
                JFrame homeFrame = (JFrame) SwingUtilities.getWindowAncestor(logoutBtn);
                if (homeFrame != null) {
                    homeFrame.dispose();
                }

                // Open login page
                SwingUtilities.invokeLater(() -> {
                    Login loginPage = new Login();
                    JFrame loginFrame = new JFrame("Login");
                    loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    loginFrame.setContentPane(loginPage.getMainPanel());
                    loginFrame.pack();
                    loginFrame.setLocationRelativeTo(null);
                    loginFrame.setVisible(true);
                });
            }
        });

        historyBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ("admin".equals(userRole) || "client".equals(userRole)) {
                    JFrame historyFrame = new JFrame("History");
                    History history = new History(userId);
                    historyFrame.setContentPane(history.getMainPanel());
                    historyFrame.setSize(600, 400);
                    historyFrame.setLocationRelativeTo(null);
                    historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    historyFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have access to the history.",
                            "Access Denied", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        claimHistoryBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ("admin".equals(userRole)) {
                    JFrame claimHistoryFrame = new JFrame("Claim History");
                    claimHistory claimHistoryPanel = new claimHistory(userId);
                    claimHistoryFrame.setContentPane(claimHistoryPanel.getMainPanel());
                    claimHistoryFrame.setSize(600, 400);
                    claimHistoryFrame.setLocationRelativeTo(null);
                    claimHistoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    claimHistoryFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Only admins can access the claim history.",
                            "Access Denied", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        categoryFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryFilter.getSelectedItem();
                DatabaseUtil.filter(tableModel, "unclaimed", selectedCategory);
            }
        });

    }

    private void categoryFilter() {
        categoryFilter.addItem("All");
        categoryFilter.addItem("Accessories");
        categoryFilter.addItem("Bags & Backpacks");
        categoryFilter.addItem("Clothing");
        categoryFilter.addItem("Electronics");
        categoryFilter.addItem("Eyewear");
        categoryFilter.addItem("Footwear");
        categoryFilter.addItem("IDs & Cards");
        categoryFilter.addItem("Keys");
        categoryFilter.addItem("Mobile Devices");
        categoryFilter.addItem("Papers & Documents");
        categoryFilter.addItem("Tumblers");
        categoryFilter.addItem("Wallets & Purses");
        categoryFilter.addItem("Others");
    }

    // I separated the listeners with a method to avoid confusion
    private void setupListeners(DefaultTableModel tableModel) {
        //AI-generated
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = table1.getSelectedRow();

                if (row >= 0) {
                    // Retrieve the ID of the selected item
                    int id = (int) tableModel.getValueAt(row, 0); // The hidden ID column
                    classDTO itemDetails = DatabaseUtil.getItemDetailsById(id);// Fetch item details

                    if (itemDetails != null) {
                        String userRole = DatabaseUtil.getUserRoleById(userId);

                        if ("admin".equals(userRole)) {
                            displayItemDetails(itemDetails);
                        } else if ("client".equals(userRole)) {
                            if (itemDetails.getReporterId() == userId) {
                                displayItemDetails(itemDetails); // Own item, allow edits
                            } else {
                                displayItemDetailsV2(itemDetails); // Not their item, view-only
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to fetch details for the selected item.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            private void displayItemDetails(classDTO itemDetails) {
                JFrame detailsFrame = new JFrame("Item Details");
                itemDetails detailsPanel = new itemDetails(itemDetails, userId);
                detailsFrame.setContentPane(detailsPanel.getMainPanel());
                detailsFrame.setSize(600, 400);
                detailsFrame.setLocationRelativeTo(null);
                detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                detailsFrame.setVisible(true);
            }

            private void displayItemDetailsV2(classDTO itemDetails) {
                JFrame detailsFrame = new JFrame("Item Details");
                itemDetailsV2 detailsPanel = new itemDetailsV2(itemDetails);
                detailsFrame.setContentPane(detailsPanel.getMainPanel());
                detailsFrame.setSize(600, 400);
                detailsFrame.setLocationRelativeTo(null);
                detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                detailsFrame.setVisible(true);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
