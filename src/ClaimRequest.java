
import com.formdev.flatlaf.FlatLightLaf;
import db.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClaimRequest {
    private JPanel mainPanel;
    private JTable table1;
    private JPanel headerPanel;
    private JLabel titleLabel;
    private JPanel bodyPanel;
    private JScrollPane tableScrollPane;

    public ClaimRequest(){
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Initialize table
        String[] columns = {"Request ID", "Item Name", "Claimer", "Request Date", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        table1.setModel(tableModel);
        DatabaseUtil.populateClaimRequest(tableModel);

    }
    private void setupListeners(DefaultTableModel tableModel) {
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table1.getSelectedRow();
                if (row >= 0) {
                    int requestId = (int) tableModel.getValueAt(row, 0);
                    String itemName = (String) tableModel.getValueAt(row, 1);
                    String claimer = (String) tableModel.getValueAt(row, 2);
                    String requestDate = tableModel.getValueAt(row, 3).toString();
                    String status = (String) tableModel.getValueAt(row, 4);

                    JFrame claimDetailsFrame = new JFrame("Claim Request Details");
                    ClaimDetails claimDetails = new ClaimDetails(requestId, itemName, claimer, requestDate, status);
                    claimDetailsFrame.setContentPane(claimDetails.getMainPanel());
                    claimDetailsFrame.setSize(600, 400);
                    claimDetailsFrame.setLocationRelativeTo(null);
                    claimDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    claimDetailsFrame.setVisible(true);
                }
            }
        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }
}
