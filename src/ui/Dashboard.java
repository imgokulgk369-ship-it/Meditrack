package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.MedicineService;

public class Dashboard {

    public void showDashboard() {

        MedicineService service = new MedicineService();

        Stage stage = new Stage();

        Button addBtn = new Button("Add Medicine");
        addBtn.setOnAction(e -> {
            new AddMedicineScreen().show();
        });

        Button viewBtn = new Button("View Medicines");
        viewBtn.setOnAction(e -> {
            new ViewMedicineScreen().show();
        });

        Button updateBtn = new Button("Update Medicine");

        updateBtn.setOnAction(e -> {
            new ViewMedicineScreen().show();
        });

        Button deleteBtn = new Button("Delete Medicine");
        deleteBtn.setOnAction(e -> {
            service.deleteMedicine(1);
        });

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                addBtn,
                viewBtn,
                updateBtn,
                deleteBtn
        );

        Scene scene = new Scene(root, 400, 350);

        stage.setTitle("MediTrack Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}