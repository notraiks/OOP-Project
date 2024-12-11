package db;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DatabaseUtil {

    public static void populate(DefaultTableModel tableModel, int userId, String statusFilter, String categoryFilter) {
        String query;
        if ("All".equals(categoryFilter) || categoryFilter == null || categoryFilter.isEmpty()) {
            query = "SELECT item_id, item_name, category, location_found, date_found, time_found, status " +
                    "FROM found_items WHERE status = ? AND user_id != ?";
        } else {
            query = "SELECT item_id, item_name, category, location_found, date_found, time_found, status " +
                    "FROM found_items WHERE status = ? AND category = ? AND user_id != ?";
        }

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, statusFilter);
            if (categoryFilter != null && !"All".equals(categoryFilter)) {
                stmt.setString(2, categoryFilter);
                stmt.setInt(3, userId);
            } else {
                stmt.setInt(2, userId);
            }

            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0);

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getString("location_found"),
                        rs.getString("date_found"),
                        rs.getString("time_found"),
                        rs.getString("status")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching data excluding user items:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void populateUserItems(DefaultTableModel tableModel, int userId, String status) {
        String query = "SELECT item_id, item_name, category, location_found, date_found, time_found, status " +
                "FROM found_items WHERE user_id = ? AND status = ?";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, status);

            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0);

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getString("location_found"),
                        rs.getString("date_found"),
                        rs.getString("time_found"),
                        rs.getString("status")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching user items:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void populateUser(DefaultTableModel tableModel) {
        String query = "SELECT user_id, fName, lName, email, phone_number, role FROM users";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0);

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("user_id"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("role")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching user data:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void populateClaimRequest(DefaultTableModel tableModel) {
        String query = "SELECT cr.request_id, fi.item_name, CONCAT(u.fName, ' ', u.lName) AS claimer, " +
                "cr.request_date, cr.status " +
                "FROM claim_requests cr " +
                "INNER JOIN found_items fi ON cr.item_id = fi.item_id " +
                "INNER JOIN users u ON cr.user_id = u.user_id";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("request_id"),
                        rs.getString("item_name"),
                        rs.getString("claimer"),
                        rs.getDate("request_date"),
                        rs.getString("status")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching claim requests:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
