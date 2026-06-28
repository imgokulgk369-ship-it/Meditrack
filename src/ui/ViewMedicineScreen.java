package ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Medicine;
import service.MedicineService;

public class ViewMedicineScreen extends Stage {

    private TableView<Medicine> table = new TableView<>();

    private TextField nameField = new TextField();
    private TextField manufacturerField = new TextField();
    private TextField categoryField = new TextField();
    private TextField batchField = new TextField();
    private TextField expiryField = new TextField();
    private TextField quantityField = new TextField();
    private TextField priceField = new TextField();

    public ViewMedicineScreen() {

        setTitle("View Medicines");

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

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldMedicine, selectedMedicine) -> {

            if (selectedMedicine != null) {
                nameField.setText(selectedMedicine.getMedicineName());
                manufacturerField.setText(selectedMedicine.getManufacturer());
                categoryField.setText(selectedMedicine.getCategory());
                batchField.setText(selectedMedicine.getBatchNo());
                expiryField.setText(selectedMedicine.getExpiryDate());
                quantityField.setText(String.valueOf(selectedMedicine.getQuantity()));
                priceField.setText(String.valueOf(selectedMedicine.getPrice()));
            }

        });

        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        updateButton.setOnAction(e -> {

            Medicine selectedMedicine = table.getSelectionModel().getSelectedItem();

            if (selectedMedicine != null) {

                selectedMedicine.setMedicineName(nameField.getText());
                selectedMedicine.setManufacturer(manufacturerField.getText());
                selectedMedicine.setCategory(categoryField.getText());
                selectedMedicine.setBatchNo(batchField.getText());
                selectedMedicine.setExpiryDate(expiryField.getText());
                selectedMedicine.setQuantity(Integer.parseInt(quantityField.getText()));
                selectedMedicine.setPrice(Double.parseDouble(priceField.getText()));

                service.updateMedicine(selectedMedicine);

                table.refresh();

                System.out.println("Medicine Updated Successfully!");
            }

        });
        deleteButton.setOnAction(e -> {

            Medicine selectedMedicine = table.getSelectionModel().getSelectedItem();

            if (selectedMedicine != null) {

                System.out.println("Selected ID = " + selectedMedicine.getMedicineId());

                System.out.println("Selected Medicine ID = " + selectedMedicine.getMedicineId());

                service.deleteMedicine(selectedMedicine.getMedicineId());

                table.getItems().remove(selectedMedicine);

                System.out.println("Medicine Deleted Successfully!");
            }

        });

        VBox form = new VBox(
                10,
                new Label("Name"), nameField,
                new Label("Manufacturer"), manufacturerField,
                new Label("Category"), categoryField,
                new Label("Batch"), batchField,
                new Label("Expiry"), expiryField,
                new Label("Quantity"), quantityField,
                new Label("Price"), priceField,
                updateButton,
                deleteButton
        );

        form.setPadding(new Insets(10));

        TextField searchField = new TextField();
        searchField.setPromptText("Search Medicine");

        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {

            table.setItems(FXCollections.observableArrayList(
                    service.searchMedicines(searchField.getText())
            ));

        });

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setPadding(new Insets(10));
        BorderPane root = new BorderPane();

        root.setTop(searchBox);
        root.setCenter(table);
        root.setRight(form);

        Scene scene = new Scene(root, 1000, 500);

        setScene(scene);
    }
}
