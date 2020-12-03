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
import Models.weapons.*;

import java.io.IOException;
import java.util.ArrayList;

public class GameManager {

	private Personnage perso;
	private Personnage ennemi;
	private static String[] noms_guerriers_ennemis = {
			"Li",
			"Runra",
			"Khazrin",
			"Gunmus",
			"Mumag",
			"Tadnos",
			"Dramo",
			"Brannrarg",
			"Drantha",
			"Bael",
			"Umthic",
			"Guli",
			"Rodro",
			"Thormae",
			"Thrilmar",
			"Durgrim",
			"Thekha",
			"Óinki",
			"Rido",
			"Dasdorn"
	};
	private static String[] noms_mages_ennemis = {
			"Lonpalny",
			"Xoben",
			"Merpe",
			"Khanne",
			"Tayrid",
			"Miale",
			"Riru",
			"Giusrinble",
			"Mava",
			"Magius",
			"Saura",
			"Delgavis",
			"Bennys",
			"Dylow",
			"Palmenea",
			"Shalan",
			"Lonnan",
			"Nanman",
			"Mily",
			"Brima"
	};
	private static String[] noms_chasseurs_ennemis = {
			"Sthly",
			"Shamo",
			"Macol",
			"Tabo",
			"Bill",
			"Asahy",
			"Yschal",
			"Ssthisi",
			"Mehe",
			"Altsu",
			"Huira",
			"Tul",
			"Hsthsber",
			"Ram",
			"Ina",
			"Losas",
			"Matli",
			"Vafosys",
			"Asro",
			"Fequet"
	};
	private ArrayList<Epee> epees_basiques;
	private ArrayList<Bouclier> boucliers_basiques;
	private ArrayList<Arc> arcs_basiques;
	private ArrayList<Sort> sorts_basiques;
	private ArrayList<SortUltime> sorts_ultimes;

	public void start() throws IOException, CreationException {
		epees_basiques = new ArrayList<>();
		epees_basiques.add(new Epee("Durandal", 10));
		epees_basiques.add(new Epee("Sobek", 15));
		epees_basiques.add(new Epee("Excalibur", 20));
		boucliers_basiques = new ArrayList<>();
		boucliers_basiques.add(new Bouclier("Bouclier en Fer", 0, 5));
		boucliers_basiques.add(new Bouclier("Bouclier en Bronze", 0, 7));
		boucliers_basiques.add(new Bouclier("Bouclier en Argent", 0, 10));
		arcs_basiques = new ArrayList<>();
		arcs_basiques.add(new Arc("Thor'Idal", 10, 20));
		arcs_basiques.add(new Arc("Atiesh", 15, 10));
		arcs_basiques.add(new Arc("Val'Anyr", 20, 15));
		sorts_basiques = new ArrayList<>();
		sorts_basiques.add(new Sort("Frappe éclair", 10, 3));
		sorts_basiques.add(new Sort("Furie", 10, 5));
		sorts_basiques.add(new Sort("Estoc", 10, 10));
		sorts_ultimes = new ArrayList<>();
		sorts_ultimes.add(new SortUltime("Flamme Interdite", 20, 15));
		sorts_ultimes.add(new SortUltime("Réveil des Arcanes", 50, 40));
		sorts_ultimes.add(new SortUltime("Tempête", 30, 20));
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
		Main.sceneLoader.switchTo(SceneLoader.SCENE_NEXT_FIGHT);
	}

	public int getNiveau() {
		return perso.getLevel();
	}

	public void saveCharacter() throws IOException {
		CharacterHandler.updateCharacter(perso);
		loadNewCharacterView();
	}

	public void loadCombat() throws GuerrierException, CreationException, MageException, ChasseurException, IOException {
		ArrayList<Arme> weapons_ennemi = new ArrayList<>();
		ArrayList<Sort> sorts_ennemi = new ArrayList<>();
		int i = (int)Math.floor(Math.random() * 3) + 1;
		switch(i) {
			case CharacterHandler.GUERRIER:
				weapons_ennemi.add(epees_basiques.get((int)Math.floor(Math.random() * epees_basiques.size())));
				weapons_ennemi.add(boucliers_basiques.get((int)Math.floor(Math.random() * boucliers_basiques.size())));
				sorts_ennemi.add(sorts_basiques.get((int)Math.floor(Math.random() * sorts_basiques.size())));
				ennemi = new Guerrier(noms_guerriers_ennemis[(int)Math.floor(Math.random() * 20)],
						perso.getLevel(),
						(int)(50 * perso.getLevel() * 1.5),
						(int)(30 * perso.getLevel() * 1.2),
						weapons_ennemi,
						sorts_ennemi
						);
				ennemi.equiperArme(ennemi.getWeapons().get(0));
				ennemi.equiperArme(ennemi.getWeapons().get(1));
				break;
			case CharacterHandler.MAGE:
				sorts_ennemi.add(sorts_basiques.get((int)Math.floor(Math.random() * sorts_basiques.size())));
				sorts_ennemi.add(sorts_ultimes.get((int)Math.floor(Math.random() * sorts_ultimes.size())));
				ennemi = new Mage(noms_mages_ennemis[(int)Math.floor(Math.random() * 20)],
						perso.getLevel(),
						(int)(50 * perso.getLevel() * 1.5),
						(int)(30 * perso.getLevel() * 1.2),
						weapons_ennemi,
						sorts_ennemi
				);
				break;
			case CharacterHandler.CHASSEUR:
				weapons_ennemi.add(arcs_basiques.get((int)Math.floor(Math.random() * arcs_basiques.size())));
				sorts_ennemi.add(sorts_basiques.get((int)Math.floor(Math.random() * sorts_basiques.size())));
				ennemi = new Chasseur(noms_guerriers_ennemis[(int)Math.floor(Math.random() * 20)],
						perso.getLevel(),
						(int)(50 * perso.getLevel() * 1.5),
						(int)(30 * perso.getLevel() * 1.2),
						weapons_ennemi,
						sorts_ennemi
				);
				ennemi.equiperArme(ennemi.getWeapons().get(0));
				break;
		}
		Main.sceneLoader.switchTo(SceneLoader.SCENE_GAME);
	}

	public Personnage getPersonnage() {
		return perso;
	}

	public Personnage getEnnemi() {
		return ennemi;
	}
}
