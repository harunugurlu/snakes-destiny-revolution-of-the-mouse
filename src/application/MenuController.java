package application;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private ImageView snakeTitleImage, mouseTitleImage;

    ImageView pause;
    @FXML
    private AnchorPane myAnchorPane;
    private Stage stage;
    private Scene scene;
    private Parent root;
    protected Button gotoMainMenu;
        Button pauseButton;
        Button buttonStart;
        Button logout;
    private Stage stageGamePlayChoosing;

    private Stage stageMainMenu;
    private Stage stageMultiPlayer;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ScaleTransition scale1 = new ScaleTransition();
        ScaleTransition scale2 = new ScaleTransition();
        scale1.setNode(snakeTitleImage);
        scale1.setDuration(Duration.millis(3000));
        scale1.setCycleCount(TranslateTransition.INDEFINITE);
        scale1.setInterpolator(Interpolator.LINEAR);
        scale1.setByX(1.0);
        scale1.setByY(1.0);
        scale1.setAutoReverse(true);
        scale1.play();
        scale2.setNode(mouseTitleImage);
        scale2.setDuration(Duration.millis(3000));
        scale2.setCycleCount(TranslateTransition.INDEFINITE);
        scale2.setInterpolator(Interpolator.LINEAR);
        scale2.setByX(1.0);
        scale2.setByY(1.0);
        scale2.setAutoReverse(true);
        scale2.play();
    }

    public void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you really want to log out");
        alert.setTitle("Logging out");
        alert.setContentText("Please click yes if you want to log out.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) myAnchorPane.getScene().getWindow();
            System.out.println("Logged out");
            stage.close();
        }
    }

    public void switchToGameModeMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GameModeMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToGameZone(ActionEvent event) throws IOException {


        AnchorPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stageMainMenu = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stageMainMenu.setScene(scene);
        stageMainMenu.show();

    }
    public void switchToGameZone2(ActionEvent event) throws IOException {


        AnchorPane root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stageMainMenu = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stageMainMenu.setScene(scene);
        stageMainMenu.show();

    }

    public void switchToPauseMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PauseMenu2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
