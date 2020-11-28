package Application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneLoader {
	private Stage stage;
	public static final int SCENE_MAIN_MENU = 0;
	public static final int SCENE_GAME = 1;

	public SceneLoader(Stage stage) {
		this.stage = stage;
	}
	
	public void switchTo(int num_scene) throws IOException {
		switch(num_scene) {
			case SCENE_MAIN_MENU:
				makeSceneMainMenu(stage);
				break;
			case SCENE_GAME:
				makeSceneGame(stage);
				break;
			default:
				break;
		}
	}

	private void makeSceneMainMenu(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/fxml/main_menu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,1280,720);
		scene.setFill(Color.WHITE);
		scene.getStylesheets().add(getClass().getResource("/Views/css/main_menu.css").toExternalForm());
		stage.setScene(scene);
	}

	private void makeSceneGame(Stage stage) throws IOException {
		System.exit(0);
	}
}
