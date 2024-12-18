package db;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ItemDB {
    public static void filter(DefaultTableModel tableModel, String statusFilter, String categoryFilter) {
        // If categoryFilter is "Filter by...", we fetch all items regardless of category
        String query;
        if ("All".equals(categoryFilter) || categoryFilter == null || categoryFilter.isEmpty()) {
            query = "SELECT item_id, item_name, category, location_found, date_found, time_found, status " +
                    "FROM found_items WHERE status = ?";
        } else {
            query = "SELECT item_id, item_name, category, location_found, date_found, time_found, status " +
                    "FROM found_items WHERE status = ? AND category = ?";
        }

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, statusFilter);
            if (categoryFilter != null && !"All".equals(categoryFilter)) {
                stmt.setString(2, categoryFilter);
            }

            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0); // Clear existing rows

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
            JOptionPane.showMessageDialog(null, "Error fetching data from database:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static classDTO getItemDetailsById(int id) {
        String query = "SELECT fi.item_id, fi.item_name, fi.category, fi.location_found, fi.date_found, fi.time_found, " +
                "fi.description, fi.status, fi.user_id, u.fName, u.lName, u.email, u.phone_number " +
                "FROM found_items fi " +
                "INNER JOIN users u ON fi.user_id = u.user_id " +
                "WHERE fi.item_id = ?";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new classDTO(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getString("location_found"),
                        rs.getString("date_found"),
                        rs.getString("time_found"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("user_id"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("email"),
                        rs.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching item details:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static boolean createItem(String name, String category, String location,
                                     String dateFound, String timeFound, String description, int userId) {
        String query = "INSERT INTO found_items (item_name, category, location_found, date_found, " +
                "time_found, description, status, user_id) VALUES (?, ?, ?, ?, ?, ?, 'unclaimed', ?)";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setString(3, location);
            stmt.setString(4, dateFound);
            stmt.setString(5, timeFound);
            stmt.setString(6, description);
            stmt.setInt(7, userId);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error inserting item into database:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Delete an item
    public static boolean deleteItem(int itemId) {
        String query = "DELETE FROM found_items WHERE item_id = ?";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, itemId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting item:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Update item details
    public static boolean updateItemDetails(int itemId, String name, String category, String location,
                                            String dateFound, String timeFound, String description) {
        String query = "UPDATE found_items SET item_name = ?, category = ?, location_found = ?, " +
                "date_found = ?, time_found = ?, description = ? WHERE item_id = ?";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setString(3, location);
            stmt.setString(4, dateFound);
            stmt.setString(5, timeFound);
            stmt.setString(6, description);
            stmt.setInt(7, itemId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating item details:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Update item status
    public static boolean claimItem(int itemId, String status) {
        String query = "UPDATE found_items SET status = ? WHERE item_id = ?";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setInt(2, itemId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating item status:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
