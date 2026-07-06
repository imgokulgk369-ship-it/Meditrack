package service;

import database.DatabaseConnection;
import model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierService {

    // Add Supplier
    public void addSupplier(Supplier supplier) {

        String sql = "INSERT INTO supplier (supplier_name, company, phone, email, address) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, supplier.getSupplierName());
            pstmt.setString(2, supplier.getCompany());
            pstmt.setString(3, supplier.getPhone());
            pstmt.setString(4, supplier.getEmail());
            pstmt.setString(5, supplier.getAddress());

            pstmt.executeUpdate();

            System.out.println("Supplier added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Suppliers
    public List<Supplier> viewSuppliers() {

        List<Supplier> suppliers = new ArrayList<>();

        String sql = "SELECT * FROM supplier";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Supplier supplier = new Supplier(
                        rs.getString("supplier_name"),
                        rs.getString("company"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                );

                supplier.setSupplierId(rs.getInt("supplier_id"));

                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    // Search Supplier
    public List<Supplier> searchSuppliers(String keyword) {

        List<Supplier> suppliers = new ArrayList<>();

        String sql = "SELECT * FROM supplier WHERE supplier_name LIKE ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Supplier supplier = new Supplier(
                        rs.getString("supplier_name"),
                        rs.getString("company"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                );

                supplier.setSupplierId(rs.getInt("supplier_id"));

                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    // Update Supplier
    public void updateSupplier(Supplier supplier) {

        String sql = "UPDATE supplier SET supplier_name=?, company=?, phone=?, email=?, address=? WHERE supplier_id=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, supplier.getSupplierName());
            pstmt.setString(2, supplier.getCompany());
            pstmt.setString(3, supplier.getPhone());
            pstmt.setString(4, supplier.getEmail());
            pstmt.setString(5, supplier.getAddress());
            pstmt.setInt(6, supplier.getSupplierId());

            pstmt.executeUpdate();

            System.out.println("Supplier updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Supplier
    public void deleteSupplier(int supplierId) {

        String sql = "DELETE FROM supplier WHERE supplier_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, supplierId);

            pstmt.executeUpdate();

            System.out.println("Supplier deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}