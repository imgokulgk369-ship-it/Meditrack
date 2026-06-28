package service;

import database.DatabaseConnection;
import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<Medicine> viewMedicines() {

        String sql = "SELECT * FROM medicine";
        List<Medicine> medicines = new ArrayList<>();

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Medicine medicine = new Medicine(
                        rs.getString("medicine_name"),
                        rs.getString("manufacturer"),
                        rs.getString("category"),
                        rs.getString("batch_no"),
                        rs.getString("expiry_date"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                        medicine.setMedicineId(rs.getInt("medicine_id"));

                medicines.add(medicine);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }

    public List<Medicine> searchMedicines(String keyword) {

        List<Medicine> medicines = new ArrayList<>();

        String sql = "SELECT * FROM medicine WHERE medicine_name LIKE ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Medicine medicine = new Medicine(
                        rs.getString("medicine_name"),
                        rs.getString("manufacturer"),
                        rs.getString("category"),
                        rs.getString("batch_no"),
                        rs.getString("expiry_date"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );

                medicine.setMedicineId(rs.getInt("medicine_id"));

                medicines.add(medicine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicines;
    }


    public void updateMedicine(Medicine medicine) {

        String sql = "UPDATE medicine SET medicine_name = ?, manufacturer = ?, category = ?, batch_no = ?, expiry_date = ?, quantity = ?, price = ? WHERE medicine_id = ?";

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
            pstmt.setInt(8, medicine.getMedicineId());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Medicine updated successfully!");
            } else {
                System.out.println("Medicine not found.");
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
