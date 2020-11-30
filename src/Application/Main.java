package Application;

import Models.Exceptions.*;
import Models.classes.Chasseur;
import Models.classes.Guerrier;
import Models.classes.Mage;
import Models.weapons.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
	
	public static SceneLoader sceneLoader;
	public static GameManager gameManager;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			sceneLoader = new SceneLoader(primaryStage);
			gameManager = new GameManager();
			primaryStage.setTitle("SerVOx");
			sceneLoader.switchTo(SceneLoader.SCENE_MAIN_MENU);
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
