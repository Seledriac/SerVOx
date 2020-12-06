package Application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static SceneLoader sceneLoader;
	public static GameManager gameManager;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			sceneLoader = new SceneLoader(primaryStage);
			gameManager = new GameManager();
			gameManager.start();
			primaryStage.setTitle("SerVOx");
			primaryStage.getIcons().add(new Image("/Views/Images/icone.jpg"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
