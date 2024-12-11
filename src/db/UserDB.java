package db;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class UserDB {
    public static int validateLogin(String email, String password) {
        String query = "SELECT user_id FROM users WHERE email = ? AND password = ?";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    return rs.getInt("user_id"); // Return userId if credentials are valid
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error validating login credentials:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1; // Return -1 if login fails
    }

    public static String getUserRoleById(int userId) {
        String role = null;
        String query = "SELECT role FROM users WHERE user_id = ?";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                role = rs.getString("role");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching user role:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return role;
    }

    public static boolean createUser(String fName, String lName, String email, String phone, String password, String role) {
        String query = "INSERT INTO users (fName, lName, email, phone_number, password, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, password);
            stmt.setString(6, role);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting data into database:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    public static classDTO getUser(int userId) {
        String query = "SELECT u.fName, u.lName, u.email, u.phone_number " +
                "FROM users u WHERE u.user_id = ?";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");

                return new classDTO(-1, null, null, null, null, null, null, null, -1, fName, lName, email, phone);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching user details: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static boolean updateUser(int userId, String fName, String lName, String email, String phone, String password) {
        StringBuilder query = new StringBuilder("UPDATE users SET fName = ?, lName = ?, email = ?, phone_number = ?");
        if (password != null && !password.isEmpty()) {
            query.append(", password = ?");
        }
        query.append(" WHERE user_id = ?");

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, email);
            stmt.setString(4, phone);

            if (password != null && !password.isEmpty()) {
                stmt.setString(5, password);
                stmt.setInt(6, userId);
            } else {
                stmt.setInt(5, userId);
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating user details: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Delete user
    public static boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting user: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Update user with role
    public static boolean updateUserWithRole(int userId, String fName, String lName, String email, String phone, String password, String role) {
        StringBuilder query = new StringBuilder("UPDATE users SET fName = ?, lName = ?, email = ?, phone_number = ?, role = ?");
        if (password != null && !password.isEmpty()) {
            query.append(", password = ?");
        }
        query.append(" WHERE user_id = ?");

        try (Connection conn = connectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, role);

            if (password != null && !password.isEmpty()) {
                stmt.setString(6, password);
                stmt.setInt(7, userId);
            } else {
                stmt.setInt(6, userId);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating user details: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
