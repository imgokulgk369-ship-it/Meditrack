package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Medicine;
import service.MedicineService;

public class AddMedicineScreen extends Stage {

    public AddMedicineScreen() {

        setTitle("Add Medicine");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField();
        TextField manufacturerField = new TextField();
        TextField categoryField = new TextField();
        TextField batchField = new TextField();
        TextField expiryField = new TextField();
        TextField quantityField = new TextField();
        TextField priceField = new TextField();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {

            try {

                Medicine medicine = new Medicine(
                        nameField.getText(),
                        manufacturerField.getText(),
                        categoryField.getText(),
                        batchField.getText(),
                        expiryField.getText(),
                        Integer.parseInt(quantityField.getText()),
                        Double.parseDouble(priceField.getText())
                );

                MedicineService service = new MedicineService();
                service.addMedicine(medicine);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Medicine added successfully!");
                alert.showAndWait();

            } catch (Exception ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid data.\nExpiry Date format: YYYY-MM-DD");
                alert.showAndWait();
            }

        });

        grid.add(new Label("Medicine Name"),0,0);
        grid.add(nameField,1,0);

        grid.add(new Label("Manufacturer"),0,1);
        grid.add(manufacturerField,1,1);

        grid.add(new Label("Category"),0,2);
        grid.add(categoryField,1,2);

        grid.add(new Label("Batch No"),0,3);
        grid.add(batchField,1,3);

        grid.add(new Label("Expiry Date"),0,4);
        grid.add(expiryField,1,4);

        grid.add(new Label("Quantity"),0,5);
        grid.add(quantityField,1,5);

        grid.add(new Label("Price"),0,6);
        grid.add(priceField,1,6);

        grid.add(saveButton,1,7);

        Scene scene = new Scene(grid,450,420);
        setScene(scene);
    }
}