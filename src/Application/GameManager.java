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
import Models.weapons.Bouclier;
import Models.weapons.Epee;
import Models.weapons.Sort;

import java.io.IOException;
import java.util.ArrayList;

public class GameManager {

	private Personnage perso;

	public void start() throws IOException, CreationException {
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

    public void CreateNewCharacter(String nom, int classe, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws MageException, CreationException, ChasseurException, GuerrierException, IOException {
		switch (classe) {
			case CharacterHandler.GUERRIER:
				perso = new Guerrier(nom, 1, 100, 50, weapons, sorts);
				perso.equiperArme(weapons.get(0));
				perso.equiperArme(weapons.get(1));
				break;
			case CharacterHandler.MAGE:
				perso = new Mage(nom, 1, 70, 100, weapons, sorts);
				break;
			case CharacterHandler.CHASSEUR:
				perso = new Chasseur(nom, 1, 90, 60, weapons, sorts);
				perso.equiperArme(weapons.get(0));
				break;
			default:
				break;
		}
		CharacterHandler.insertNewCharacter(perso);
		CharacterHandler.updateCharacter(perso);
		startGame();
    }

    public void chooseCharacter(Personnage perso) throws IOException {
		this.perso = perso;
		if(!(perso instanceof Mage)) {
			if(perso instanceof Guerrier) {
				for(Arme arme : perso.getWeapons()) {
					if(arme instanceof Epee) {
						perso.equiperArme(arme);
						break;
					}
				}
				for(Arme arme : perso.getWeapons()) {
					if(arme instanceof Bouclier) {
						((Guerrier)perso).equiperBouclier((Bouclier)arme);
						break;
					}
				}
			} else {
				perso.equiperArme(perso.getWeapons().get(0));
			}
		}
		startGame();
	}

    public void startGame() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_GAME);
	}

}
