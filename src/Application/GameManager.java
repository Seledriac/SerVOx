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
import ViewModels.Game;

import java.io.IOException;
import java.util.ArrayList;

public class GameManager {

	private Personnage perso;
	private Personnage ennemi;
	private int nbTours;
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
		arcs_basiques.add(new Arc("Atiesh", 15, 15));
		arcs_basiques.add(new Arc("Val'Anyr", 20, 10));
		sorts_basiques = new ArrayList<>();
		sorts_basiques.add(new Sort("Frappe éclair", 20, 10));
		sorts_basiques.add(new Sort("Furie", 20, 10));
		sorts_basiques.add(new Sort("Estoc", 20, 10));
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
				perso = new Guerrier(nom, 1, 50, 25, weapons, sorts);
				perso.equiperArme(weapons.get(0));
				((Guerrier)perso).equiperBouclier((Bouclier) perso.getWeapons().get(1));
				break;
			case CharacterHandler.MAGE:
				perso = new Mage(nom, 1, 50, 60, weapons, sorts);
				break;
			case CharacterHandler.CHASSEUR:
				perso = new Chasseur(nom, 1, 50, 25, weapons, sorts);
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
		loadChooseCharacterView();
	}

	public void loadCombat() throws GuerrierException, CreationException, MageException, ChasseurException, IOException {
		ArrayList<Arme> weapons_ennemi = new ArrayList<>();
		ArrayList<Sort> sorts_ennemi = new ArrayList<>();
		int vie_ennemi = (int)(45 * perso.getLevel());
		int mana_ennemi = (int)(45 *  perso.getLevel() * 0.6);
		int i = (int)Math.floor(Math.random() * 3) + 1;
		switch(i) {
			case CharacterHandler.GUERRIER:
				weapons_ennemi.add(epees_basiques.get((int)Math.floor(Math.random() * epees_basiques.size())));
				weapons_ennemi.add(boucliers_basiques.get((int)Math.floor(Math.random() * boucliers_basiques.size())));
				sorts_ennemi.add(sorts_basiques.get((int)Math.floor(Math.random() * sorts_basiques.size())));
				ennemi = new Guerrier(noms_guerriers_ennemis[(int)Math.floor(Math.random() * 20)],
						perso.getLevel(),
						vie_ennemi,
						mana_ennemi,
						weapons_ennemi,
						sorts_ennemi
						);
				ennemi.equiperArme(ennemi.getWeapons().get(0));
				((Guerrier)ennemi).equiperBouclier((Bouclier) ennemi.getWeapons().get(1));
				break;
			case CharacterHandler.MAGE:
				mana_ennemi = (int)(mana_ennemi * 2);
				sorts_ennemi.add(sorts_basiques.get((int)Math.floor(Math.random() * sorts_basiques.size())));
				sorts_ennemi.add(sorts_ultimes.get((int)Math.floor(Math.random() * sorts_ultimes.size())));
				ennemi = new Mage(noms_mages_ennemis[(int)Math.floor(Math.random() * 20)],
						perso.getLevel(),
						vie_ennemi,
						mana_ennemi,
						weapons_ennemi,
						sorts_ennemi
				);
				break;
			case CharacterHandler.CHASSEUR:
				weapons_ennemi.add(arcs_basiques.get((int)Math.floor(Math.random() * arcs_basiques.size())));
				sorts_ennemi.add(sorts_basiques.get((int)Math.floor(Math.random() * sorts_basiques.size())));
				ennemi = new Chasseur(noms_guerriers_ennemis[(int)Math.floor(Math.random() * 20)],
						perso.getLevel(),
						vie_ennemi,
						mana_ennemi,
						weapons_ennemi,
						sorts_ennemi
				);
				ennemi.equiperArme(ennemi.getWeapons().get(0));
				break;
		}
		nbTours = 0;
		Main.sceneLoader.switchTo(SceneLoader.SCENE_GAME);
	}

	public Personnage getPersonnage() {
		return perso;
	}

	public Personnage getEnnemi() {
		return ennemi;
	}

	public void attaqueJoueur(Game game, Arme arme) throws IOException, InterruptedException {
		nbTours++;
		boolean critique = false;
		if(Math.random() * 100 < 10)
			critique = true;
		if(arme instanceof Sort) {
			perso.sort(ennemi, ((Sort) arme), critique);
		}
		else {
			perso.cac(ennemi, critique);
		}
		String logs_append = "";
		logs_append += perso.getNom() + " attaque " + ennemi.getNom() + " avec " + arme.getNom() + " (" + arme.getDamages() + " pts de dégât)";
		if(critique)
			logs_append += "\nCoût critique !";
		logs_append += "\n";
		game.addLogs(logs_append);
		if(ennemi.getHealth() <= 0) {
			game.addLogs("Victoire !\n");
			perso.setLevel(perso.getLevel() + 1);
			perso.setMax_health(((int)(perso.getMax_health() + 50)));
			perso.setMax_mana((int)(perso.getMax_mana() + 30));
			if(perso instanceof Mage)
				perso.setMax_mana(perso.getMax_mana() * 2);
			perso.setHealth(perso.getMax_health());
			perso.setMana(perso.getMax_mana());
			for(Arme weapon : perso.getWeapons()) {
				if(weapon instanceof Bouclier)
					((Bouclier) weapon).setDefense(((Bouclier) weapon).getDefense() + 1);
				else
					weapon.setDamages(weapon.getDamages() + 3);
			}
			for(Sort sort : perso.getSorts()) {
				sort.setDamages(sort.getDamages() + 3);
			}
			if(perso.getArme_equipee() instanceof Arc)
				((Arc)perso.getArme_equipee()).setFleches(((Arc)perso.getArme_equipee()).getFleches_max());
			CharacterHandler.updateCharacter(perso);
			startGame();
		} else {
			attaqueEnnemi(game);
		}
	}

	public void attaqueEnnemi(Game game) throws IOException, InterruptedException {
		String logs_append = "";
		boolean critique = false;
		if(Math.random() * 100 < 20)
			critique = true;
		int choix_ennemi = (int)(Math.random() * 2);
		if (choix_ennemi == 0 || ennemi instanceof Mage) {
			Sort sort_choisi = ennemi.getSorts().get((int) (Math.random() * ennemi.getSorts().size()));
			logs_append += ennemi.getNom() + " attaque " + perso.getNom() + " avec " + sort_choisi.getNom() + " (" + sort_choisi.getDamages() + " pts de dégât)";
			ennemi.sort(perso, sort_choisi, critique);
		} else {
			logs_append += ennemi.getNom() + " attaque " + perso.getNom() + " avec " + ennemi.getArme_equipee().getNom()  + " (" + ennemi.getArme_equipee().getDamages() + " pts de dégât)";
			ennemi.cac(perso, critique);
		}
		if(critique)
			logs_append += "\nCoût critique !";
		logs_append += "\n";
		game.addLogs(logs_append);
		if(perso.getHealth() <= 0) {
			game.addLogs("Défaite.\n");
			loadChooseCharacterView();
		} else {
			if(perso instanceof Mage)
				perso.setMana(perso.getMana() + 3);
			else
				perso.setMana(perso.getMana() + 1);
			if(nbTours % 3 == 0) {
				if (perso instanceof Chasseur)
					((Arc) perso.getArme_equipee()).setFleches(((Arc) perso.getArme_equipee()).getFleches() + 1);
			}
			if(ennemi instanceof Mage)
				ennemi.setMana(ennemi.getMana() + 3);
			else
				ennemi.setMana(ennemi.getMana() + 1);
			if(nbTours % 3 == 0)
				if(ennemi instanceof Chasseur)
					((Arc)ennemi.getArme_equipee()).setFleches(((Arc)ennemi.getArme_equipee()).getFleches() + 1);
			game.reprendreCombat();
		}
	}

	public void finDeTourJoueur(Game game) throws IOException, InterruptedException {
		attaqueEnnemi(game);
	}
}
