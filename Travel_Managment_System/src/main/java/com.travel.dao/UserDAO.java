package com.travel.dao;

import com.travel.model.User;
import java.sql.*;

public class UserDAO {
    
    public User loginUser(String username, String password) {
        Connection conn = null;
        User user = null;
        
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("full_name"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
        
        return user;
    }


    private static class DBConnection {
        private static final String URL = System.getProperty("DB_URL", "jdbc:h2:mem:travel;DB_CLOSE_DELAY=-1");
        private static final String USER = System.getProperty("DB_USER", "sa");
        private static final String PASS = System.getProperty("DB_PASS", "");

        static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASS);
        }

        static void closeConnection(Connection conn) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
        }
    }
    
    public boolean registerUser(User user) {
        Connection conn = null;
        
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO users (username, password, email, full_name, phone, address, role) VALUES (?, ?, ?, ?, ?, ?, 'customer')";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getFullName());
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getAddress());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.closeConnection(conn);
        }
    }
}
