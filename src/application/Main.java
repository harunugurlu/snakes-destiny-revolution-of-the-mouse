package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			AnchorPane root = loader.load();
			SampleController controller = loader.getController();
			Scene scene = new Scene(root,600,600,Color.GREY);
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					switch(event.getCode()) {
					case F:
						
					{controller.eat();}
						break;
					case W:
			
							{controller.up("w");}
						break;
					case S:
					
							{controller.down("s");}
						
						break;
					case D:
						
							{controller.right("d");}
						
						break;
					case A:
	
							{controller.left("a");}
						break;
						default:
							break;
					}
				}
			});
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
