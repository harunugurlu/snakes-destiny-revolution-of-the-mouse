package application;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("resources/MainMenu.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("resources/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setHeight(800);
            primaryStage.setWidth(1200);
            primaryStage.show();
            primaryStage.setResizable(false);
            primaryStage.setFullScreen(false);
            primaryStage.setOnCloseRequest(event -> {
                event.consume();
                logout(primaryStage);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you really want to log out");
        alert.setTitle("Logging out");
        alert.setContentText("Please click yes if you want to log out.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Logged out");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
