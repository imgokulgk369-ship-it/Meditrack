package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import service.MedicineService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Dashboard {

    public void showDashboard() {

        Stage stage = new Stage();

        Button backBtn = new Button("⬅ BACK");
        backBtn.setStyle(
                "-fx-background-color:#455A64;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:15px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:10;"
        );

        backBtn.setOnAction(e -> {
            stage.close();
            new LoginScreen().start(new Stage());
        });

        // Title
        Label welcome = new Label("WELCOME TO");
        welcome.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 34));
        welcome.setTextFill(Color.web("#0D47A1"));

        Label title = new Label("MEDITRACK");
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 52));
        title.setTextFill(Color.web("#1565C0"));
        title.setStyle("-fx-effect:dropshadow(gaussian, rgba(0,0,0,0.6),10,0.7,4,4);");

        Label subtitle = new Label("PHARMACY MANAGEMENT SYSTEM");
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        subtitle.setTextFill(Color.web("#37474F"));

        Label clockLabel = new Label();

        clockLabel.setStyle(
                "-fx-font-size:18;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#0D47A1;"
        );
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd MMM yyyy    hh:mm:ss a");

        Timeline clock = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    clockLabel.setText(LocalDateTime.now().format(formatter));
                })
        );

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();

        // Dashboard Cards
        MedicineService service = new MedicineService();

        Label medicineCount = new Label(
                "💊 Medicines\n" + service.getMedicineCount()
        );

        Label lowStock = new Label(
                "⚠ Low Stock\n" + service.getLowStockCount()
        );

        Label supplierCount = new Label(
                "📦 Suppliers"
        );

        Label expiryCount = new Label(
                "⏰ Expiring\n" + service.getExpiringCount()
        );

        String cardStyle =
                "-fx-background-color:white;" +
                        "-fx-border-color:#1565C0;" +
                        "-fx-border-radius:12;" +
                        "-fx-background-radius:12;" +
                        "-fx-font-size:18;" +
                        "-fx-font-weight:bold;" +
                        "-fx-padding:20;" +
                        "-fx-alignment:center;" +
                        "-fx-min-width:180;" +
                        "-fx-min-height:100;";

        medicineCount.setStyle(cardStyle);
        lowStock.setStyle(cardStyle);
        supplierCount.setStyle(cardStyle);
        expiryCount.setStyle(cardStyle);

        HBox row1 = new HBox(20, medicineCount, lowStock);
        row1.setAlignment(Pos.CENTER);

        HBox row2 = new HBox(20, supplierCount, expiryCount);
        row2.setAlignment(Pos.CENTER);

        // Buttons
        Button medicineBtn = new Button("💊 Medicine Management");
        Button supplierBtn = new Button("📦 Supplier Management");
        Button billingBtn = new Button("💰 Billing");
        Button reportsBtn = new Button("📊 Reports");
        Button logoutBtn = new Button("🚪 Logout");

        HBox topBar = new HBox();

        Label space = new Label("                MEDITRACK");

        topBar.getChildren().addAll(backBtn, space, logoutBtn);

        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(40);
        topBar.setPadding(new Insets(15));

        String blueButton =
                "-fx-background-color:#1976D2;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:12px;" +
                        "-fx-pref-width:320px;" +
                        "-fx-pref-height:45px;";

        medicineBtn.setStyle(blueButton);
        supplierBtn.setStyle(blueButton);
        billingBtn.setStyle(blueButton);
        reportsBtn.setStyle(blueButton);

        logoutBtn.setStyle(
                "-fx-background-color:#D32F2F;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:12px;" +
                        "-fx-pref-width:320px;" +
                        "-fx-pref-height:45px;"
        );


        // Actions
        medicineBtn.setOnAction(e -> new ViewMedicineScreen().show());

        supplierBtn.setOnAction(e -> {
            new ViewSupplierScreen().show();
        });

        billingBtn.setOnAction(e ->
                System.out.println("Billing Module Coming Soon"));

        reportsBtn.setOnAction(e ->
                System.out.println("Reports Module Coming Soon"));

        logoutBtn.setOnAction(e -> {

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);

            confirm.setTitle("Logout");
            confirm.setHeaderText(null);
            confirm.setContentText("Are you sure you want to logout?");

            if (confirm.showAndWait().get() == ButtonType.OK) {

                stage.close();
                new LoginScreen().start(new Stage());

            }

        });
        // Root Layout
        VBox root = new VBox(25);
        Label footer = new Label("© 2026 MediTrack Pharmacy Management System");
        footer.setStyle(
                "-fx-font-size:12px;" +
                        "-fx-text-fill:gray;"
        );
        root.setAlignment(Pos.CENTER);
        root.
                setPadding(new Insets(30));

        root.setBackground(new Background(
                new BackgroundFill(
                        Color.web("#E3F2FD"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));
        Button aboutBtn = new Button("ℹ About");
        aboutBtn.setStyle(
                "-fx-background-color:#00897B;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:12;"
        );
        aboutBtn.setOnAction(e -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("About MediTrack");
            alert.setHeaderText("MediTrack Pharmacy Management System");

            alert.setContentText(
                    "Version : 1.0\n\n" +
                            "Developed Using:\n\n" +
                            "• Java\n" +
                            "• JavaFX\n" +
                            "• PostgreSQL\n" +
                            "• JDBC\n\n" +
                            "Mini Project 2026"
            );

            alert.showAndWait();

        });

        root.getChildren().addAll(

                topBar,

                welcome,
                title,
                subtitle,
                clockLabel,

                row1,
                row2,

                medicineBtn,
                supplierBtn,
                billingBtn,
                reportsBtn,
                footer
        );
        Scene scene = new Scene(root, 900, 650);

        try {
            scene.getStylesheets().add(
                    getClass().getResource("/style.css").toExternalForm()
            );
        } catch (Exception ignored) {
        }

        stage.setTitle("MediTrack Dashboard");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
    }
}