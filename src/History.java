import com.formdev.flatlaf.FlatLightLaf;
import db.DatabaseUtil;
import db.ItemDB;
import db.classDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class History {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel titleLabel;
    private JPanel bodyPanel;
    private JTable table1;
    private JScrollPane tableScrollPane;

    private int userId;


    public History(int userId) {
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
        DatabaseUtil.populateUserItems(tableModel, userId, "claimed");

        setupListeners(tableModel);

    }
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
                    classDTO itemDetails = ItemDB.getItemDetailsById(id); // Fetch item details

                    if (itemDetails != null) {
                        JFrame detailsFrame = new JFrame("Item Details");
                        itemDetails detailsPanel = new itemDetails(itemDetails, userId); // Pass the data object
                        detailsFrame.setContentPane(detailsPanel.getMainPanel());
                        detailsFrame.setSize(600, 400);
                        detailsFrame.setLocationRelativeTo(null);
                        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        detailsFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to fetch details for the selected item.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}