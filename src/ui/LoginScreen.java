package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    @Override
    public void start(Stage stage) {

        // Pharmacy Icon
        Label icon = new Label("💊");
        icon.setStyle("-fx-font-size:60;");

        // Title
        Label title = new Label("MEDITRACK");
        title.setStyle(
                "-fx-font-size:38;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#1565C0;"
        );

        // Subtitle
        Label subtitle = new Label("PHARMACY MANAGEMENT SYSTEM");
        subtitle.setStyle(
                "-fx-font-size:18;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#455A64;"
        );

        // Username
        TextField username = new TextField();
        username.setPromptText("Enter Username");
        username.setMaxWidth(280);

        // Password
        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        password.setMaxWidth(280);

        // Login Button
        Button loginButton = new Button("LOGIN");

        loginButton.setStyle(
                "-fx-background-color:#1976D2;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:16;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:10;" +
                        "-fx-pref-width:280;" +
                        "-fx-pref-height:42;"
        );

        loginButton.setOnAction(e -> {

            Dashboard dashboard = new Dashboard();
            dashboard.showDashboard();
            stage.close();

        });

        // Footer
        Label footer = new Label("© 2026 MediTrack Pharmacy Management System");

        footer.setStyle(
                "-fx-text-fill:gray;" +
                        "-fx-font-size:12;"
        );

        VBox root = new VBox(18);

        root.setAlignment(Pos.CENTER);

        root.setStyle(
                "-fx-background-color:#E3F2FD;"
        );

        root.getChildren().addAll(
                icon,
                title,
                subtitle,
                username,
                password,
                loginButton,
                footer
        );

        Scene scene = new Scene(root, 500, 450);

        stage.setTitle("MediTrack Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}