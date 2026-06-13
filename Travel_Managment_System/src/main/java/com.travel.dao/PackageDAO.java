package com.travel.dao;

import com.travel.model.Package;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackageDAO {
    
    public List<Package> getAllPackages() {
        List<Package> packages = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM packages";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Package pkg = new Package();
                pkg.setPackageId(rs.getInt("package_id"));
                pkg.setPackageName(rs.getString("package_name"));
                pkg.setDestination(rs.getString("destination"));
                pkg.setDurationDays(rs.getInt("duration_days"));
                pkg.setDescription(rs.getString("description"));
                pkg.setPrice(rs.getBigDecimal("price"));
                pkg.setImageUrl(rs.getString("image_url"));
                packages.add(pkg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        DBConnection.closeConnection(conn);
        return packages;
    }
    
    public Package getPackageById(int packageId) {
        Connection conn = null;
        Package pkg = null;
        
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM packages WHERE package_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, packageId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                pkg = new Package();
                pkg.setPackageId(rs.getInt("package_id"));
                pkg.setPackageName(rs.getString("package_name"));
                pkg.setDestination(rs.getString("destination"));
                pkg.setDurationDays(rs.getInt("duration_days"));
                pkg.setDescription(rs.getString("description"));
                pkg.setPrice(rs.getBigDecimal("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        DBConnection.closeConnection(conn);
        return pkg;
    }
}
