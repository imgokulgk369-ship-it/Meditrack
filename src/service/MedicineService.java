package service;

import database.DatabaseConnection;
import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}