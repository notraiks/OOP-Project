import com.formdev.flatlaf.FlatLightLaf;

import db.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserManagement {
    private JPanel mainPanel;
    private JTable table1;
    private JLabel titleLabel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JScrollPane tableScrollPane;

    private int userId;

    public UserManagement(){

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Initialize table
        String[] columns = {"ID", "First Name", "Last Name", "Email", "Phone Number", "Role"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Set the table model and hide the item_id column
        table1.setModel(tableModel);
        table1.removeColumn(table1.getColumnModel().getColumn(0)); // Hide the ID column
        DatabaseUtil.populateUser(tableModel);

        setupListeners(tableModel);
    }

    private void setupListeners(DefaultTableModel tableModel) {
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table1.getSelectedRow();
                if (row >= 0) {
                    int selectedUserId = (int) tableModel.getValueAt(row, 0);

                    JFrame userDetailsFrame = new JFrame("User Details");
                    UserDetails userDetails = new UserDetails(selectedUserId);
                    userDetailsFrame.setContentPane(userDetails.getMainPanel());
                    userDetailsFrame.setSize(600, 400);
                    userDetailsFrame.setLocationRelativeTo(null);
                    userDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    userDetailsFrame.setVisible(true);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
