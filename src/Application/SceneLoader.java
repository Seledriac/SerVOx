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
	public static final int SCENE_MAIN_MENU_CHOOSE = 1;
	public static final int SCENE_NEW_CHARACTER = 2;
	public static final int SCENE_NEW_GUERRIER = 3;
	public static final int SCENE_NEW_MAGE = 4;
	public static final int SCENE_NEW_CHASSEUR = 5;
	public static final int SCENE_CHOOSE_CHARACTER = 6;
	public static final int SCENE_GAME = 7;

	public SceneLoader(Stage stage) {
		this.stage = stage;
	}
	
	public void switchTo(int num_scene) throws IOException {
		switch(num_scene) {
			case SCENE_MAIN_MENU:
				makeSceneMainMenu(stage);
				break;
			case SCENE_MAIN_MENU_CHOOSE:
				makeSceneMainMenuChoose(stage);
				break;
			case SCENE_NEW_CHARACTER:
				makeSceneNewCharacter(stage);
				break;
			case SCENE_NEW_GUERRIER:
				makeSceneNewGuerrier(stage);
				break;
			case SCENE_NEW_MAGE:
				makeSceneNewMage(stage);
				break;
			case SCENE_NEW_CHASSEUR:
				makeSceneNewChasseur(stage);
				break;
			case SCENE_CHOOSE_CHARACTER:
				makeSceneChooseCharacter(stage);
				break;
			case SCENE_GAME:
				makeSceneGame(stage);
				break;
			default:
				break;
		}
	}

	private Scene makeBasicScene(String FXMLpath) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLpath));
		Parent root = loader.load();
		Scene scene = new Scene(root,1280,720);
		scene.setFill(Color.WHITE);
		return scene;
	}

	private void makeSceneMainMenu(Stage stage) throws IOException {
		Scene scene_main_menu = makeBasicScene("/Views/fxml/main_menu.fxml");
		stage.setScene(scene_main_menu);
	}

	private void makeSceneMainMenuChoose(Stage stage) throws IOException {
		Scene scene_main_menu_choose = makeBasicScene("/Views/fxml/main_menu_choose.fxml");
		stage.setScene(scene_main_menu_choose);
	}

	private void makeSceneNewCharacter(Stage stage) throws IOException {
		Scene scene_new_character = makeBasicScene("/Views/fxml/new_character.fxml");
		stage.setScene(scene_new_character);
	}

	private void makeSceneNewGuerrier(Stage stage) throws IOException {
		Scene scene_new_guerrier = makeBasicScene("/Views/fxml/new_guerrier.fxml");
		scene_new_guerrier.getStylesheets().add(getClass().getResource("/Views/css/new_guerrier.css").toExternalForm());
		stage.setScene(scene_new_guerrier);
	}

	private void makeSceneNewMage(Stage stage) throws IOException {
		Scene scene_new_mage = makeBasicScene("/Views/fxml/new_mage.fxml");
		scene_new_mage.getStylesheets().add(getClass().getResource("/Views/css/new_mage.css").toExternalForm());
		stage.setScene(scene_new_mage);
	}

	private void makeSceneNewChasseur(Stage stage) throws IOException {
		Scene scene_new_chasseur = makeBasicScene("/Views/fxml/new_chasseur.fxml");
		scene_new_chasseur.getStylesheets().add(getClass().getResource("/Views/css/new_chasseur.css").toExternalForm());
		stage.setScene(scene_new_chasseur);
	}

	private void makeSceneChooseCharacter(Stage stage) throws IOException {
		Scene scene_choose_character = makeBasicScene("/Views/fxml/choose_character.fxml");
		stage.setScene(scene_choose_character);
	}

	private void makeSceneGame(Stage stage) throws IOException {
		Scene scene_game = makeBasicScene("/Views/fxml/game.fxml");
		stage.setScene(scene_game);
	}

}
