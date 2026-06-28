package ui;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Medicine;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import service.MedicineService;

public class ViewMedicineScreen extends Stage {

    public ViewMedicineScreen() {

        setTitle("View Medicines");

        TableView<Medicine> table = new TableView<>();
        TableColumn<Medicine, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("medicineName"));

        TableColumn<Medicine, String> manufacturerCol = new TableColumn<>("Manufacturer");
        manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        TableColumn<Medicine, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Medicine, String> batchCol = new TableColumn<>("Batch");
        batchCol.setCellValueFactory(new PropertyValueFactory<>("batchNo"));

        TableColumn<Medicine, String> expiryCol = new TableColumn<>("Expiry");
        expiryCol.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

        TableColumn<Medicine, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Medicine, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(
                nameCol,
                manufacturerCol,
                categoryCol,
                batchCol,
                expiryCol,
                quantityCol,
                priceCol
        );

        MedicineService service = new MedicineService();

        table.setItems(FXCollections.observableArrayList(service.viewMedicines()));

        BorderPane root = new BorderPane();
        root.setCenter(table);

        Scene scene = new Scene(root, 800, 500);

        setScene(scene);
    }
}