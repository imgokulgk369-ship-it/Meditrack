package service;

import database.DatabaseConnection;
import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class MedicineService {

    public void addMedicine(Medicine medicine) {

        String sql = "INSERT INTO medicine " +
                "(medicine_name, manufacturer, category, batch_no, expiry_date, quantity, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, medicine.getMedicineName());
            pstmt.setString(2, medicine.getManufacturer());
            pstmt.setString(3, medicine.getCategory());
            pstmt.setString(4, medicine.getBatchNo());
            pstmt.setDate(5, java.sql.Date.valueOf(medicine.getExpiryDate()));
            pstmt.setInt(6, medicine.getQuantity());
            pstmt.setDouble(7, medicine.getPrice());

            pstmt.executeUpdate();

            System.out.println("Medicine added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewMedicines() {

        String sql = "SELECT * FROM medicine";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                System.out.println("----------------------------");
                System.out.println("ID: " + rs.getInt("medicine_id"));
                System.out.println("Name: " + rs.getString("medicine_name"));
                System.out.println("Manufacturer: " + rs.getString("manufacturer"));
                System.out.println("Category: " + rs.getString("category"));
                System.out.println("Batch No: " + rs.getString("batch_no"));
                System.out.println("Expiry Date: " + rs.getDate("expiry_date"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Price: " + rs.getDouble("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateMedicine(int medicineId, double newPrice, int newQuantity) {

        String sql = "UPDATE medicine SET price = ?, quantity = ? WHERE medicine_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, newQuantity);
            pstmt.setInt(3, medicineId);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Medicine updated successfully!");
            } else {
                System.out.println("Medicine ID not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteMedicine(int medicineId) {

        String sql = "DELETE FROM medicine WHERE medicine_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, medicineId);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Medicine deleted successfully!");
            } else {
                System.out.println("Medicine ID not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
