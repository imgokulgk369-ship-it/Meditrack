package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Supplier;
import service.SupplierService;

public class AddSupplierScreen extends Stage {

    public AddSupplierScreen() {

        setTitle("Add Supplier");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField supplierNameField = new TextField();
        TextField companyField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();
        TextField addressField = new TextField();

        Button saveButton = new Button("SAVE");

        saveButton.setStyle(
                "-fx-background-color:#1976D2;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:14;" +
                        "-fx-font-weight:bold;"
        );

        saveButton.setOnAction(e -> {

            try {

                Supplier supplier = new Supplier(
                        supplierNameField.getText(),
                        companyField.getText(),
                        phoneField.getText(),
                        emailField.getText(),
                        addressField.getText()
                );

                SupplierService service = new SupplierService();
                service.addSupplier(supplier);
                System.out.println("Supplier Added Successfully");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Supplier added successfully!");
                alert.showAndWait();

                supplierNameField.clear();
                companyField.clear();
                phoneField.clear();
                emailField.clear();
                addressField.clear();

            } catch (Exception ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid supplier details.");
                alert.showAndWait();
                this.close();

            }

        });

        grid.add(new Label("Supplier Name"), 0, 0);
        grid.add(supplierNameField, 1, 0);

        grid.add(new Label("Company"), 0, 1);
        grid.add(companyField, 1, 1);

        grid.add(new Label("Phone"), 0, 2);
        grid.add(phoneField, 1, 2);

        grid.add(new Label("Email"), 0, 3);
        grid.add(emailField, 1, 3);

        grid.add(new Label("Address"), 0, 4);
        grid.add(addressField, 1, 4);

        grid.add(saveButton, 1, 5);

        Scene scene = new Scene(grid, 450, 350);
        setScene(scene);
    }
}