package ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Supplier;
import service.SupplierService;

public class ViewSupplierScreen extends Stage {

    private TableView<Supplier> table = new TableView<>();

    private TextField supplierNameField = new TextField();
    private TextField companyField = new TextField();
    private TextField phoneField = new TextField();
    private TextField emailField = new TextField();
    private TextField addressField = new TextField();

    public ViewSupplierScreen() {

        setTitle("Supplier Management");

        TableColumn<Supplier, String> nameCol =
                new TableColumn<>("Supplier Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("supplierName"));

        TableColumn<Supplier, String> companyCol =
                new TableColumn<>("Company");
        companyCol.setCellValueFactory(
                new PropertyValueFactory<>("company"));

        TableColumn<Supplier, String> phoneCol =
                new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<>("phone"));

        TableColumn<Supplier, String> emailCol =
                new TableColumn<>("Email");
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        TableColumn<Supplier, String> addressCol =
                new TableColumn<>("Address");
        addressCol.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        table.getColumns().addAll(
                nameCol,
                companyCol,
                phoneCol,
                emailCol,
                addressCol
        );

        SupplierService service = new SupplierService();

        table.setItems(
                FXCollections.observableArrayList(
                        service.viewSuppliers()
                )
        );

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSupplier, selectedSupplier) -> {

                    if (selectedSupplier != null) {

                        supplierNameField.setText(
                                selectedSupplier.getSupplierName());

                        companyField.setText(
                                selectedSupplier.getCompany());

                        phoneField.setText(
                                selectedSupplier.getPhone());

                        emailField.setText(
                                selectedSupplier.getEmail());

                        addressField.setText(
                                selectedSupplier.getAddress());
                    }

                }
        );

        Button updateButton = new Button("✔ UPDATE");
        Button deleteButton = new Button("🗑 DELETE");

        updateButton.setStyle(
                "-fx-background-color:#2E7D32;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-weight:bold;"
        );

        deleteButton.setStyle(
                "-fx-background-color:#D32F2F;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-weight:bold;"
        );

        updateButton.setOnAction(e -> {

            Supplier selectedSupplier = table.getSelectionModel().getSelectedItem();

            if (selectedSupplier != null) {

                selectedSupplier.setSupplierName(supplierNameField.getText());
                selectedSupplier.setCompany(companyField.getText());
                selectedSupplier.setPhone(phoneField.getText());
                selectedSupplier.setEmail(emailField.getText());
                selectedSupplier.setAddress(addressField.getText());

                service.updateSupplier(selectedSupplier);

                table.setItems(FXCollections.observableArrayList(service.viewSuppliers()));
                table.refresh();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Supplier updated successfully!");
                alert.showAndWait();
            }

        });

        deleteButton.setOnAction(e -> {

            Supplier selectedSupplier = table.getSelectionModel().getSelectedItem();

            if (selectedSupplier != null) {

                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Delete Supplier");
                confirm.setHeaderText(null);
                confirm.setContentText("Are you sure you want to delete this supplier?");

                if (confirm.showAndWait().get() == ButtonType.OK) {

                    service.deleteSupplier(selectedSupplier.getSupplierId());

                    table.setItems(FXCollections.observableArrayList(service.viewSuppliers()));

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Supplier deleted successfully!");
                    alert.showAndWait();
                }
            }

        });

        Label formTitle = new Label("SUPPLIER DETAILS");
        formTitle.setStyle(
                "-fx-font-size:18;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#1565C0;"
        );

        VBox form = new VBox(
                10,
                formTitle,
                new Label("Supplier Name"), supplierNameField,
                new Label("Company"), companyField,
                new Label("Phone"), phoneField,
                new Label("Email"), emailField,
                new Label("Address"), addressField,
                updateButton,
                deleteButton
        );

        form.setPadding(new Insets(10));

        TextField searchField = new TextField();
        searchField.setPromptText("Search Supplier");


        Button addButton = new Button("➕ ADD SUPPLIER");

        addButton.setStyle(
                "-fx-background-color:#1976D2;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:14;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:8;"
        );

        addButton.setOnAction(e -> {
            new AddSupplierScreen().show();
        });

        Button searchButton = new Button("🔍 SEARCH");

        searchButton.setOnAction(e -> {

            table.setItems(FXCollections.observableArrayList(
                    service.searchSuppliers(searchField.getText())
            ));

        });

        Button refreshButton = new Button("🔄 REFRESH");

        refreshButton.setOnAction(e -> {

            table.setItems(FXCollections.observableArrayList(
                    service.viewSuppliers()
            ));

            searchField.clear();

        });

        HBox searchBox = new HBox(
                10,
                addButton,
                searchField,
                searchButton,
                refreshButton
        );

        searchBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();

        root.setTop(searchBox);
        root.setCenter(table);
        root.setRight(form);

        Scene scene = new Scene(root, 1000, 500);

        setScene(scene);
    }
}