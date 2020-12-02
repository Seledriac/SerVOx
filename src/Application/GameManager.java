package Application;


import Models.DB.CharacterHandler;
import Models.DB.WeaponHandler;
import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.classes.Chasseur;
import Models.classes.Guerrier;
import Models.classes.Mage;
import Models.classes.Personnage;
import Models.weapons.Arme;
import Models.weapons.Sort;

import java.io.IOException;
import java.util.ArrayList;

public class GameManager {

	private Personnage perso;

	public void start() throws IOException {
		CharacterHandler.createTableCharacters();
		WeaponHandler.createTableWeapons();
		CharacterHandler.createTableArmePersonnage();
		Main.sceneLoader.switchTo(SceneLoader.SCENE_MAIN_MENU);
	}

	public void loadMainMenuChooseView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_MAIN_MENU_CHOOSE);
	}

	public void loadNewCharacterView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_NEW_CHARACTER);
	}

	public void loadNewGuerrierView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_NEW_GUERRIER);
	}

	public void loadNewMageView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_NEW_MAGE);
	}

	public void loadNewChasseurView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_NEW_CHASSEUR);
	}

	public void loadChooseCharacterView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_CHOOSE_CHARACTER);
	}

	public ArrayList<Personnage> loadCharacters() {
		return CharacterHandler.listCharacters(Integer.MAX_VALUE);
	}

    public void CreateNewCharacter(String nom, int classe) throws MageException, CreationException, ChasseurException, GuerrierException, IOException {
		switch (classe) {
			case CharacterHandler.GUERRIER:
				perso = new Guerrier(nom, 1, 100, 50, new ArrayList<Arme>(), new ArrayList<Sort>());
				break;
			case CharacterHandler.MAGE:
				perso = new Mage(nom, 1, 70, 100, new ArrayList<Arme>(), new ArrayList<Sort>());
				break;
			case CharacterHandler.CHASSEUR:
				perso = new Chasseur(nom, 1, 90, 60, new ArrayList<Arme>(), new ArrayList<Sort>());
				break;
			default:
				break;
		}
		CharacterHandler.insertNewCharacter(perso);
		startGame();
    }

    public void chooseCharacter(Personnage perso) throws IOException {
		this.perso = perso;
		startGame();
	}

    public void startGame() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_GAME);
	}

}
