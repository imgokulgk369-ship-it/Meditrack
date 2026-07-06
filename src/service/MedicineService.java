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
import javafx.scene.control.Alert;

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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Medicine added successfully!");
            alert.showAndWait();

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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Medicine updated successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Medicine not found.");
                alert.showAndWait();
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Medicine deleted successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Medicine ID not found.");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMedicineCount() {

        String sql = "SELECT COUNT(*) FROM medicine";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getLowStockCount() {

        String sql = "SELECT COUNT(*) FROM medicine WHERE quantity < 10";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getExpiringCount() {

        String sql = "SELECT COUNT(*) FROM medicine WHERE expiry_date <= CURRENT_DATE + INTERVAL '30 days'";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
