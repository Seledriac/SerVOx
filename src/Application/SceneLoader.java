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
	public static final int SCENE_NOUVEAU_PERSONNAGE = 2;
	public static final int SCENE_NOUVEAU_GUERRIER = 3;
	public static final int SCENE_NOUVEAU_MAGE = 4;
	public static final int SCENE_NOUVEAU_CHASSEUR = 5;
	public static final int SCENE_CHOISIR_PERSONNAGE = 6;
	public static final int SCENE_GAME = 7;
	public static final int SCENE_PROCHAIN_COMBAT = 8;
	public static final int SCENE_CHOISIR_HISTOIRE = 9;
	public static final int SCENE_FIN_HISTOIRE = 10;
	public static final int SCENE_BOUTIQUE = 11;
	public static final int SCENE_INVENTAIRE = 12;

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
			case SCENE_NOUVEAU_PERSONNAGE:
				makeSceneNouveauPersonnage(stage);
				break;
			case SCENE_NOUVEAU_GUERRIER:
				makeSceneNouveauGuerrier(stage);
				break;
			case SCENE_NOUVEAU_MAGE:
				makeSceneNouveauMage(stage);
				break;
			case SCENE_NOUVEAU_CHASSEUR:
				makeSceneNouveauChasseur(stage);
				break;
			case SCENE_CHOISIR_PERSONNAGE:
				makeSceneChoisirPersonnage(stage);
				break;
			case SCENE_GAME:
				makeSceneGame(stage);
				break;
			case SCENE_PROCHAIN_COMBAT:
				makeSceneProchainCombat(stage);
				break;
			case SCENE_CHOISIR_HISTOIRE:
				makeSceneChoisirHistoire(stage);
				break;
			case SCENE_FIN_HISTOIRE:
				makeSceneFinHistoire(stage);
				break;
			case SCENE_BOUTIQUE:
				makeSceneBoutique(stage);
				break;
			case SCENE_INVENTAIRE:
				makeSceneInventaire(stage);
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

	private void makeSceneNouveauPersonnage(Stage stage) throws IOException {
		Scene scene_nouveau_personnage = makeBasicScene("/Views/fxml/nouveau_personnage.fxml");
		stage.setScene(scene_nouveau_personnage);
	}

	private void makeSceneNouveauGuerrier(Stage stage) throws IOException {
		Scene scene_nouveau_guerrier = makeBasicScene("/Views/fxml/nouveau_guerrier.fxml");
		scene_nouveau_guerrier.getStylesheets().add(getClass().getResource("/Views/css/nouveau_guerrier.css").toExternalForm());
		stage.setScene(scene_nouveau_guerrier);
	}

	private void makeSceneNouveauMage(Stage stage) throws IOException {
		Scene scene_nouveau_mage = makeBasicScene("/Views/fxml/nouveau_mage.fxml");
		scene_nouveau_mage.getStylesheets().add(getClass().getResource("/Views/css/nouveau_mage.css").toExternalForm());
		stage.setScene(scene_nouveau_mage);
	}

	private void makeSceneNouveauChasseur(Stage stage) throws IOException {
		Scene scene_nouveau_chasseur = makeBasicScene("/Views/fxml/nouveau_chasseur.fxml");
		scene_nouveau_chasseur.getStylesheets().add(getClass().getResource("/Views/css/nouveau_chasseur.css").toExternalForm());
		stage.setScene(scene_nouveau_chasseur);
	}

	private void makeSceneChoisirPersonnage(Stage stage) throws IOException {
		Scene scene_choisir_personnage = makeBasicScene("/Views/fxml/choisir_personnage.fxml");
		stage.setScene(scene_choisir_personnage);
	}

	private void makeSceneGame(Stage stage) throws IOException {
		Scene scene_game = makeBasicScene("/Views/fxml/game.fxml");
		scene_game.getStylesheets().add(getClass().getResource("/Views/css/game.css").toExternalForm());
		stage.setScene(scene_game);
	}

	private void makeSceneProchainCombat(Stage stage) throws IOException {
		Scene scene_prochain_combat = makeBasicScene("/Views/fxml/prochain_combat.fxml");
		stage.setScene(scene_prochain_combat);
	}

	private void makeSceneChoisirHistoire(Stage stage) throws IOException {
		Scene scene_choisir_histoire = makeBasicScene("/Views/fxml/choisir_histoire.fxml");
		scene_choisir_histoire.getStylesheets().add(getClass().getResource("/Views/css/choisir_histoire.css").toExternalForm());
		stage.setScene(scene_choisir_histoire);
	}

	private void makeSceneFinHistoire(Stage stage) throws IOException {
		Scene scene_fin_histoire = makeBasicScene("/Views/fxml/fin_histoire.fxml");
		stage.setScene(scene_fin_histoire);
	}

	private void makeSceneBoutique(Stage stage) throws IOException {
		Scene scene_boutique = makeBasicScene("/Views/fxml/boutique.fxml");
		stage.setScene(scene_boutique);
	}

	private void makeSceneInventaire(Stage stage) throws IOException {
		Scene scene_inventaire = makeBasicScene("/Views/fxml/inventaire.fxml");
		stage.setScene(scene_inventaire);
	}

}
