package db;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ClaimDB {
    public static boolean createClaimRequest(int itemId, int userId) {
        String query = "INSERT INTO claim_requests (item_id, user_id, request_date, status) VALUES (?, ?, NOW(), 'pending')";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, itemId);
            stmt.setInt(2, userId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error creating claim request:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean approveClaimRequest(int requestId) {
        String updateClaimRequest = "UPDATE claim_requests SET status = 'Approved' WHERE request_id = ?";
        String updateFoundItem = "UPDATE found_items SET status = 'Claimed' " +
                "WHERE item_id = (SELECT item_id FROM claim_requests WHERE request_id = ?)";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt1 = conn.prepareStatement(updateClaimRequest);
             PreparedStatement stmt2 = conn.prepareStatement(updateFoundItem)) {

            // Update claim request status to "Approved"
            stmt1.setInt(1, requestId);

            // Update found item status to "Claimed"
            stmt2.setInt(1, requestId);

            conn.setAutoCommit(false); // Start transaction

            int rowsUpdated1 = stmt1.executeUpdate();
            int rowsUpdated2 = stmt2.executeUpdate();

            conn.commit(); // Commit transaction

            return rowsUpdated1 > 0 && rowsUpdated2 > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error approving claim request:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
