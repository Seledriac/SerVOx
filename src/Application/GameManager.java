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
	private int niveau;
	public static final int GUERRIER = 1;
	public static final int MAGE = 2;
	public static final int CHASSEUR = 3;

	public void start() throws IOException {
		CharacterHandler.createTableCharacters();
//		WeaponHandler.createTableWeapons();
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
		loadCharacters();
		Main.sceneLoader.switchTo(SceneLoader.SCENE_CHOOSE_CHARACTER);
	}
	private void loadCharacters() throws IOException {

	}

    public void CreateNewCharacter(String nom, int classe, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws MageException, CreationException, ChasseurException, GuerrierException, IOException {
		switch (classe) {
			case GUERRIER:
				perso = new Guerrier(nom, 100, 50, 1, weapons, sorts);
				System.out.println("Création d'un guerrier");
				break;
			case MAGE:
				perso = new Mage(nom, 70, 100, 1, weapons, sorts);
				System.out.println("Création d'un mage");
				break;
			case CHASSEUR:
				perso = new Chasseur(nom, 90, 60, 1, weapons, sorts);
				System.out.println("Création d'un chasseur");
				break;
			default:
				break;
		}
		niveau = 1;
		CharacterHandler.insertNewCharacter(perso);
		startGame(1);
    }

    public void loadCharacter(String nom, int classe, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws MageException, CreationException, ChasseurException, GuerrierException, IOException {
		switch (classe) {
			case GUERRIER:
				perso = new Guerrier(nom, 100, 50, 1, weapons, sorts);
				System.out.println("Création d'un guerrier");
				break;
			case MAGE:
				perso = new Mage(nom, 70, 100, 1, weapons, sorts);
				System.out.println("Création d'un mage");
				break;
			case CHASSEUR:
				perso = new Chasseur(nom, 90, 60, 1, weapons, sorts);
				System.out.println("Création d'un chasseur");
				break;
			default:
				break;
		}
		niveau = 1;
		startGame(1);
	}

    public void startGame(int niveau) throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_GAME);
	}

}
