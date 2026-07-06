package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainLayout extends Stage {

    private BorderPane root = new BorderPane();

    public MainLayout() {

        setTitle("MediTrack");

        VBox menu = new VBox(15);
        menu.setAlignment(Pos.TOP_CENTER);
        menu.setPrefWidth(220);

        Button dashboardBtn = new Button("🏠 Dashboard");
        Button medicineBtn = new Button("💊 Medicines");
        Button supplierBtn = new Button("📦 Suppliers");
        Button billingBtn = new Button("💰 Billing");
        Button reportsBtn = new Button("📊 Reports");
        Button logoutBtn = new Button("🚪 Logout");

        dashboardBtn.setMaxWidth(Double.MAX_VALUE);
        medicineBtn.setMaxWidth(Double.MAX_VALUE);
        supplierBtn.setMaxWidth(Double.MAX_VALUE);
        billingBtn.setMaxWidth(Double.MAX_VALUE);
        reportsBtn.setMaxWidth(Double.MAX_VALUE);
        logoutBtn.setMaxWidth(Double.MAX_VALUE);

        menu.getChildren().addAll(
                dashboardBtn,
                medicineBtn,
                supplierBtn,
                billingBtn,
                reportsBtn,
                logoutBtn
        );

        root.setLeft(menu);

        Scene scene = new Scene(root, 1200, 700);

        setScene(scene);
    }
}