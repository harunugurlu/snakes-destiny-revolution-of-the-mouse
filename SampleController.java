package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;


import javafx.fxml.Initializable;


import javafx.scene.image.ImageView;


import javafx.util.Duration;

public class SampleController implements Initializable{
	
	
	
	

	
	
	
	
	@FXML
	private ImageView snakeTitleImage, mouseTitleImage,selectGameModeImage;
	
	@FXML
	
	private Button buttonExit,singlePlayerButton,multiPlayerButton;
	
	@FXML
	
	private AnchorPane scenePane;
	private Stage stage;
	

	public void exit(ActionEvent event) {
		
		
			stage = (Stage) scenePane.getScene().getWindow();
			stage.close();
			
	
	
}
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		
		ScaleTransition scale1 = new ScaleTransition();
		ScaleTransition scale2 = new ScaleTransition();
		TranslateTransition scale3 = new TranslateTransition();
		
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
		
		scale3.setNode(selectGameModeImage);
		scale3.setDuration(Duration.millis(1500));
		scale3.setCycleCount(TranslateTransition.INDEFINITE);
		scale3.setByZ(50);
		scale3.setByY(50);
		scale3.setAutoReverse(true);
		scale3.play();

		
	}
	private Stage stageGamePlayChoosing;
	private Scene scene;
	private Stage stageMainMenu;
	
	
	
	
	public void switchToGamePlayChoosingScreen(ActionEvent event) throws IOException {
		AnchorPane root = FXMLLoader.load(getClass().getResource("GamePlayChoosing.fxml"));
	
		stageGamePlayChoosing = (Stage)((Node)event.getSource()).getScene().getWindow();
	
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stageGamePlayChoosing.setScene(scene);
		stageGamePlayChoosing.show();
	}
	public void switchToMenuScreen(ActionEvent event) throws IOException {
		AnchorPane root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
	
		stageMainMenu = (Stage)((Node)event.getSource()).getScene().getWindow();
	
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stageMainMenu.setScene(scene);
		stageMainMenu.show();
	}
	
}
