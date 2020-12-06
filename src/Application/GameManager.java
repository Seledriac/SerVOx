package Application;

import Models.DB.PersonnagesDBHandler;
import Models.DB.ItemsDBHandler;
import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.classes.*;
import Models.items.*;
import ViewModels.Game;
import java.io.*;
import java.util.ArrayList;

public class GameManager {

	private Personnage perso;
	private Personnage ennemi;
	private int nbTours;
	private String[] noms_guerriers_ennemis = {
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
	private String[] noms_mages_ennemis = {
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
	private String[] noms_chasseurs_ennemis = {
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
	private ArrayList<Item> items_du_jeu;
	private ArrayList<Bouclier> boucliers_basiques;
	private ArrayList<Epee> epees_basiques;
	private ArrayList<Arc> arcs_basiques;
	private ArrayList<SortOffensif> sorts_mage_starter;
	private ArrayList<SortOffensif> sorts_guerrier_basiques;
	private ArrayList<SortOffensif> sorts_mage_basiques;
	private ArrayList<SortOffensif> sorts_chasseur_basiques;
	private Guerrier[] histoire_guerriers;
	private Mage[] histoire_mages;
	private Chasseur[] histoire_chasseurs;

	public void start() throws IOException, CreationException, GuerrierException, ClassNotFoundException, MageException, ChasseurException {
		boucliers_basiques = new ArrayList<>();
		boucliers_basiques.add(new Bouclier(Accessibilite.GUERRIERS, "Bouclier en Fer", 100, 5));
		epees_basiques = new ArrayList<>();
		epees_basiques.add(new Epee(Accessibilite.GUERRIERS, "Durandal", 100, 10));
		epees_basiques.add(new Epee(Accessibilite.GUERRIERS, "Sobek", 100, 15));
		epees_basiques.add(new Epee(Accessibilite.GUERRIERS, "Excalibur", 100, 20));
		arcs_basiques = new ArrayList<>();
		arcs_basiques.add(new Arc(Accessibilite.CHASSEURS, "Thor'Idal", 100, 15, 10));
		arcs_basiques.add(new Arc(Accessibilite.CHASSEURS, "Atiesh", 100, 17, 7));
		arcs_basiques.add(new Arc(Accessibilite.CHASSEURS, "Val'Anyr", 100, 20, 5));
		sorts_mage_starter = new ArrayList<>();
		sorts_mage_starter.add(new SortOffensif(Accessibilite.MAGES, "Flamme Interdite", 100, 30, 20));
		sorts_mage_starter.add(new SortOffensif(Accessibilite.MAGES,"Réveil des Arcanes", 100, 50, 40));
		sorts_mage_starter.add(new SortOffensif(Accessibilite.MAGES,"Tempête", 100, 35, 25));
		sorts_guerrier_basiques = new ArrayList<>();
		sorts_guerrier_basiques.add(new SortOffensif(Accessibilite.GUERRIERS, "Démacia", 100, 20, 10));
		sorts_mage_basiques = new ArrayList<>();
		sorts_mage_basiques.add(new SortOffensif(Accessibilite.MAGES,"Petite Boule de Feu", 100, 20, 10));
		sorts_chasseur_basiques = new ArrayList<>();
		sorts_chasseur_basiques.add(new SortOffensif(Accessibilite.CHASSEURS,"Traque", 100, 20, 10));
		items_du_jeu = new ArrayList<>();
		items_du_jeu.addAll(boucliers_basiques);
		items_du_jeu.addAll(epees_basiques);
		items_du_jeu.addAll(arcs_basiques);
		items_du_jeu.addAll(sorts_mage_starter);
		items_du_jeu.addAll(sorts_guerrier_basiques);
		items_du_jeu.addAll(sorts_mage_basiques);
		items_du_jeu.addAll(sorts_chasseur_basiques);
		items_du_jeu.add(new Marteau(Accessibilite.GUERRIERS, "Mjöllnir", 200, 30));
		items_du_jeu.add(new Arbalete(Accessibilite.CHASSEURS, "Thyrse", 200, 35, 15));
		items_du_jeu.add(new Epee(Accessibilite.GUERRIERS, "Lame Tonerre", 200, 25));
		items_du_jeu.add(new Boost(Accessibilite.MAGES, "Boost", 100, 20));
		items_du_jeu.add(new Affaiblissement(Accessibilite.MAGES, "Affaiblissement", 100, 20));
//		 Création des guerriers pour l'histoire guerriers
//		for(int i = 1; i <= noms_guerriers_ennemis.length; i++) {
//			ArrayList<Item> items_ennemi = new ArrayList<>();
//			int vie_ennemi = 45 * i;
//			int mana_ennemi = (int) (45 * i * 0.6);
//			items_ennemi.add(new Epee(epees_basiques.get((int)(Math.floor(Math.random() * epees_basiques.size())))));
//			items_ennemi.add(new Bouclier(boucliers_basiques.get((int)(Math.floor(Math.random() * boucliers_basiques.size())))));
//			items_ennemi.add(new SortOffensif(sorts_guerrier_basiques.get((int)(Math.floor(Math.random() * sorts_guerrier_basiques.size())))));
//			Guerrier guerrier_ennemi = new Guerrier(noms_guerriers_ennemis[(int)(Math.floor(Math.random() * noms_guerriers_ennemis.length))],
//					0,
//					i,
//					vie_ennemi,
//					mana_ennemi,
//					items_ennemi,
//					null
//			);
//			guerrier_ennemi.getArme_equipee().setDegats(guerrier_ennemi.getArme_equipee().getDegats() + i);
//			guerrier_ennemi.getBouclier_equipe().setDefense(guerrier_ennemi.getBouclier_equipe().getDefense() + i);
//			((SortOffensif)items_ennemi.get(2)).setDegats(((SortOffensif)items_ennemi.get(2)).getDegats() + 2 * i);
//			System.out.println(guerrier_ennemi);
//			FileOutputStream fos_guerriers = new FileOutputStream("src/Models/classes/ennemis/guerriers/guerrier_" + String.valueOf(i));
//			ObjectOutputStream oos_guerriers = new ObjectOutputStream(fos_guerriers);
//			oos_guerriers.writeObject(guerrier_ennemi);
//		}
//		System.out.println("\n");
//		for(int i = 1; i <= noms_guerriers_ennemis.length; i++) {
//			FileInputStream fis_guerriers = new FileInputStream("src/Models/classes/ennemis/guerriers/guerrier_" + String.valueOf(i));
//			ObjectInputStream ois_guerriers = new ObjectInputStream(fis_guerriers);
//			Guerrier guerrier_ennemi = (Guerrier)ois_guerriers.readObject();
//			System.out.println(guerrier_ennemi);
//		}
//		 Création des mages
//		for(int i = 1; i <= noms_mages_ennemis.length; i++) {
//			ArrayList<Item> items_ennemi = new ArrayList<>();
//			int vie_ennemi = 45 * i;
//			int mana_ennemi = (int) (45 * i * 0.6);
//			items_ennemi.add(new SortOffensif(sorts_mage_basiques.get((int)(Math.floor(Math.random() * sorts_mage_basiques.size())))));
//			items_ennemi.add(new SortOffensif(sorts_mage_starter.get((int)(Math.floor(Math.random() * sorts_mage_starter.size())))));
//			Mage mage_ennemi = new Mage(noms_mages_ennemis[(int)(Math.floor(Math.random() * noms_mages_ennemis.length))],
//					0,
//					i,
//					vie_ennemi,
//					mana_ennemi,
//					items_ennemi,
//					null
//			);
//			((SortOffensif)items_ennemi.get(0)).setDegats(((SortOffensif)items_ennemi.get(0)).getDegats() + i);
//			((SortOffensif)items_ennemi.get(1)).setDegats(((SortOffensif)items_ennemi.get(1)).getDegats() + 2 * i);
//			System.out.println(mage_ennemi);
//			FileOutputStream fos_mages = new FileOutputStream("src/Models/classes/ennemis/mages/mage_" + String.valueOf(i));
//			ObjectOutputStream oos_mages = new ObjectOutputStream(fos_mages);
//			oos_mages.writeObject(mage_ennemi);
//		}
//		System.out.println("\n");
//		for(int i = 1; i <= noms_mages_ennemis.length; i++) {
//			FileInputStream fis_mages = new FileInputStream("src/Models/classes/ennemis/mages/mage_" + String.valueOf(i));
//			ObjectInputStream ois_mages = new ObjectInputStream(fis_mages);
//			Mage mage_ennemi = (Mage)ois_mages.readObject();
//			System.out.println(mage_ennemi);
//		}
//		Création des chasseurs
//		for(int i = 1; i <= noms_chasseurs_ennemis.length; i++) {
//			ArrayList<Item> items_ennemi = new ArrayList<>();
//			int vie_ennemi = 45 * i;
//			int mana_ennemi = (int) (45 * i * 0.6);
//			items_ennemi.add(new Arc(arcs_basiques.get((int)(Math.floor(Math.random() * arcs_basiques.size())))));
//			items_ennemi.add(new SortOffensif(sorts_chasseur_basiques.get((int)(Math.floor(Math.random() * sorts_chasseur_basiques.size())))));
//			Chasseur chasseur_ennemi = new Chasseur(noms_chasseurs_ennemis[(int)(Math.floor(Math.random() * noms_chasseurs_ennemis.length))],
//					0,
//					i,
//					vie_ennemi,
//					mana_ennemi,
//					items_ennemi,
//					null
//			);
//			chasseur_ennemi.getArme_equipee().setDegats(chasseur_ennemi.getArme_equipee().getDegats() + i);
//			((SortOffensif)items_ennemi.get(1)).setDegats(((SortOffensif)items_ennemi.get(1)).getDegats() + 2 * i);
//			System.out.println(chasseur_ennemi);
//			FileOutputStream fos_chasseurs = new FileOutputStream("src/Models/classes/ennemis/chasseurs/chasseur_" + String.valueOf(i));
//			ObjectOutputStream oos_chasseurs = new ObjectOutputStream(fos_chasseurs);
//			oos_chasseurs.writeObject(chasseur_ennemi);
//		}
//		System.out.println("\n");
//		for(int i = 1; i <= noms_chasseurs_ennemis.length; i++) {
//			FileInputStream fis_chasseurs = new FileInputStream("src/Models/classes/ennemis/chasseurs/chasseur_" + String.valueOf(i));
//			ObjectInputStream ois_chasseurs = new ObjectInputStream(fis_chasseurs);
//			Chasseur chasseur_ennemi = (Chasseur)ois_chasseurs.readObject();
//			System.out.println(chasseur_ennemi);
//		}
//		Récupération des banques d'ennemis
		histoire_guerriers = new Guerrier[noms_guerriers_ennemis.length];
		histoire_mages = new Mage[noms_mages_ennemis.length];
		histoire_chasseurs = new Chasseur[noms_chasseurs_ennemis.length];
		for(int i = 0; i < noms_guerriers_ennemis.length; i++) {
			FileInputStream fis_guerriers = new FileInputStream("src/Models/classes/ennemis/guerriers/guerrier_" + String.valueOf(i + 1));
			ObjectInputStream ois_guerriers = new ObjectInputStream(fis_guerriers);
			Guerrier guerrier_charge = (Guerrier) ois_guerriers.readObject();
			histoire_guerriers[i] = guerrier_charge;
		}
		for(int i = 0; i < noms_mages_ennemis.length; i++) {
			FileInputStream fis_mages = new FileInputStream("src/Models/classes/ennemis/mages/mage_" + String.valueOf(i + 1));
			ObjectInputStream ois_mages = new ObjectInputStream(fis_mages);
			Mage mage_charge = (Mage) ois_mages.readObject();
			histoire_mages[i] = mage_charge;
		}
		for(int i = 0; i < noms_chasseurs_ennemis.length; i++) {
			FileInputStream fis_chasseurs = new FileInputStream("src/Models/classes/ennemis/chasseurs/chasseur_" + String.valueOf(i + 1));
			ObjectInputStream ois_chasseurs = new ObjectInputStream(fis_chasseurs);
			Chasseur chasseur_charge = (Chasseur) ois_chasseurs.readObject();
			histoire_chasseurs[i] = chasseur_charge;
		}
		PersonnagesDBHandler.creerTablePersonnage();
		ItemsDBHandler.creerTableItem();
		PersonnagesDBHandler.creerTableItemPersonnage();
		Main.sceneLoader.switchTo(SceneLoader.SCENE_MAIN_MENU);
	}

	public ArrayList<Item> getItems_du_jeu() {
		return items_du_jeu;
	}

	public void loadMainMenuChooseView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_MAIN_MENU_CHOOSE);
	}

	public void loadNouveauPersonnageView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_NOUVEAU_PERSONNAGE);
	}

	public void loadNouveauGuerrierView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_NOUVEAU_GUERRIER);
	}

	public void loadNouveauMageView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_NOUVEAU_MAGE);
	}

	public void loadNouveauChasseurView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_NOUVEAU_CHASSEUR);
	}

	public void loadChoisirPersonnageView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_CHOISIR_PERSONNAGE);
	}

	public void loadBoutiqueView() throws IOException{
		Main.sceneLoader.switchTo(SceneLoader.SCENE_BOUTIQUE);
	}

	public void loadInventaireView() throws IOException{
		Main.sceneLoader.switchTo(SceneLoader.SCENE_INVENTAIRE);
	}

	public ArrayList<Personnage> chargerPersonnages() {
		return PersonnagesDBHandler.listPersonnages();
	}

    public void creerNouveauPersonnage(TypePerso classe, String nom, ArrayList<Item> items) throws MageException, CreationException, ChasseurException, GuerrierException, IOException {
		switch (classe) {
			case GUERRIER:
				perso = new Guerrier(nom, 0, 1, 50, 25, items, Histoire.RANDOM);
				break;
			case MAGE:
				perso = new Mage(nom, 0, 1, 50, 60, items, Histoire.RANDOM);
				break;
			case CHASSEUR:
				perso = new Chasseur(nom, 0, 1, 50, 25, items, Histoire.RANDOM);
				break;
			default:
				break;
		}
		PersonnagesDBHandler.insererNouveauPersonnage(perso);
		PersonnagesDBHandler.updatePersonnage(perso);
		loadChoisirHistoireView();
    }

	public void loadChoisirHistoireView() throws IOException {
		Main.sceneLoader.switchTo(SceneLoader.SCENE_CHOISIR_HISTOIRE);
	}

	public void setHistoire(Histoire histoire) throws IOException {
		perso.setHistoire(histoire);
		PersonnagesDBHandler.updatePersonnage(perso);
		startGame();
	}

    public void choisirPersonnage(Personnage perso) throws IOException {
		this.perso = perso;
		startGame();
	}

    public void startGame() throws IOException {
		if(perso.getNiveau() > 20) {
			Main.sceneLoader.switchTo(SceneLoader.SCENE_FIN_HISTOIRE);
		} else
			Main.sceneLoader.switchTo(SceneLoader.SCENE_PROCHAIN_COMBAT);
	}

	public int getNiveau() {
		return perso.getNiveau();
	}

	public void savePersonnage() throws IOException {
		PersonnagesDBHandler.updatePersonnage(perso);
		loadChoisirPersonnageView();
	}

	public void loadCombat() throws GuerrierException, CreationException, MageException, ChasseurException, IOException {
		if(perso.getHistoire() == Histoire.RANDOM) {
			ArrayList<Item> items_ennemi = new ArrayList<>();
			int vie_ennemi = 45 * perso.getNiveau();
			int mana_ennemi = (int) (45 * perso.getNiveau() * 0.6);
			TypePerso classe_ennemi = TypePerso.valueOf((int)(Math.floor(Math.random() * 3) + 1));
			switch(classe_ennemi) {
				case GUERRIER:
					items_ennemi.add(epees_basiques.get((int)(Math.floor(Math.random() * epees_basiques.size()))));
					items_ennemi.add(boucliers_basiques.get((int)(Math.floor(Math.random() * boucliers_basiques.size()))));
					items_ennemi.add(sorts_guerrier_basiques.get((int)(Math.floor(Math.random() * sorts_guerrier_basiques.size()))));
					ennemi = new Guerrier(noms_guerriers_ennemis[(int)(Math.floor(Math.random() * noms_guerriers_ennemis.length))],
							0,
							perso.getNiveau(),
							vie_ennemi,
							mana_ennemi,
							items_ennemi,
							null
					);
					break;
				case MAGE:
					mana_ennemi = mana_ennemi * 2;
					items_ennemi.add(sorts_mage_basiques.get((int)(Math.floor(Math.random() * sorts_mage_basiques.size()))));
					items_ennemi.add(sorts_mage_starter.get((int)(Math.floor(Math.random() * sorts_mage_starter.size()))));
					ennemi = new Mage(noms_mages_ennemis[(int)(Math.floor(Math.random() * noms_mages_ennemis.length))],
							0,
							perso.getNiveau(),
							vie_ennemi,
							mana_ennemi,
							items_ennemi,
							null
					);
					break;
				case CHASSEUR:
					items_ennemi.add(arcs_basiques.get((int)(Math.floor(Math.random() * arcs_basiques.size()))));
					items_ennemi.add(sorts_chasseur_basiques.get((int)(Math.floor(Math.random() * sorts_chasseur_basiques.size()))));
					ennemi = new Chasseur(noms_guerriers_ennemis[(int)(Math.floor(Math.random() * noms_guerriers_ennemis.length))],
							0,
							perso.getNiveau(),
							vie_ennemi,
							mana_ennemi,
							items_ennemi,
							null
					);
					break;
			}
			for(Item item : ennemi.getItems()) {
				if(item instanceof Arme) {
					((Arme)item).setDegats(((Arme) item).getDegats() + perso.getNiveau() * 2);
				}
			}
		} else {
			switch(perso.getHistoire()) {
				case GUERRIERS:
					ennemi = histoire_guerriers[perso.getNiveau() - 1];
					break;
				case MAGES:
					ennemi = histoire_mages[perso.getNiveau() - 1];
					break;
				case CHASSEURS:
					ennemi = histoire_chasseurs[perso.getNiveau() - 1];
					break;
				default:
					break;
			}
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

	public void actionJoueur(Game game, Item item) throws IOException, InterruptedException {
		nbTours++;
		boolean critique = false;
		if(Math.random() * 100 < 5)
			critique = true;
		if(item instanceof Sort) {
			perso.lancerSort((Sort)item, ennemi, critique);
		} else if(item instanceof SortOffensif) {
			perso.attaqueSort(((SortOffensif)item), ennemi, critique);
		} else {
			if(perso instanceof Guerrier) {
				((Guerrier)perso).attaqueArme(ennemi, critique);
			} else if(perso instanceof Chasseur){
				((Chasseur)perso).attaqueArme(ennemi, critique);
			}
		}
		String logs_append = "";
		logs_append += perso.getNom() + " utilise " + item.getNom();
		if(item instanceof Arme)
			logs_append += " (" + ((Arme)item).getDegats() + " pts de dégât)";
		if(critique)
			logs_append += "\nCoût critique !";
		logs_append += "\n";
		game.addLogs(logs_append);
		if(ennemi.getVie() <= 0) {
			game.addLogs("Victoire !\n");
			perso.setArgent(perso.getArgent() + 50);
			perso.setNiveau(perso.getNiveau() + 1);
			perso.setVie_max(perso.getVie_max() + 50);
			perso.setMana_max(perso.getMana_max() + 10);
			if(perso instanceof Mage) {
				perso.setMana_max(perso.getMana_max() + 10);
			} else {
				Arme arme_equipee;
				if(perso instanceof Guerrier) {
					arme_equipee = ((Guerrier)perso).getArme_equipee();
				} else {
					arme_equipee = ((Chasseur)perso).getArme_equipee();
				}
				if(arme_equipee instanceof ArmeAMunitions)
					((ArmeAMunitions)arme_equipee).setMunitions(((ArmeAMunitions)arme_equipee).getMunitions_max());
			}
			perso.setVie(perso.getVie_max());
			perso.setMana(perso.getMana_max());
			for(Item item1 : perso.getItems()) {
				if(item1 instanceof Bouclier) {
					((Bouclier)item1).setDefense(((Bouclier)item1).getDefense() + 1);
				}
				else if(item1 instanceof SortOffensif)
					((SortOffensif)item1).setDegats(((SortOffensif)item1).getDegats() + 3);
				else if(item1 instanceof Arme)
					((Arme)item1).setDegats(((Arme)item1).getDegats() + 2);
			}
			PersonnagesDBHandler.updatePersonnage(perso);
			startGame();
		} else {
			attaqueEnnemi(game);
		}
	}

	public void attaqueEnnemi(Game game) throws IOException, InterruptedException {
		String logs_append = "";
		boolean critique = false;
		if(Math.random() * 100 < 5)
			critique = true;
		int choix_ennemi = (int)(Math.random() * 2);
		if(choix_ennemi == 0 || ennemi instanceof Mage) {
			ArrayList<SortOffensif> sorts_possibles = new ArrayList<>();
			for(Item item : ennemi.getItems()) {
				if(item instanceof SortOffensif)
					sorts_possibles.add((SortOffensif)item);
			}
			SortOffensif sort_choisi = sorts_possibles.get((int)(Math.random() * sorts_possibles.size()));
			logs_append += ennemi.getNom() + " utilise " + sort_choisi.getNom() + " (" + sort_choisi.getDegats() + " pts de dégât)";
			ennemi.attaqueSort(sort_choisi, perso, critique);
		} else {
			if(ennemi instanceof Guerrier) {
				logs_append += ennemi.getNom() + " utilise " + ((Guerrier)ennemi).getArme_equipee().getNom() + " (" + ((Guerrier)ennemi).getArme_equipee().getDegats() + " pts de dégât)";
				((Guerrier)ennemi).attaqueArme(perso, critique);
			} else {
				logs_append += ennemi.getNom() + " utilise " + ((Chasseur)ennemi).getArme_equipee().getNom() + " (" + ((Chasseur)ennemi).getArme_equipee().getDegats() + " pts de dégât)";
				((Chasseur)ennemi).attaqueArme(perso, critique);
			}
		}
		if(critique)
			logs_append += "\nCoût critique !";
		logs_append += "\n";
		game.addLogs(logs_append);
		if(perso.getVie() <= 0) {
			game.addLogs("Défaite.\n");
			loadChoisirPersonnageView();
		} else {
			if(perso instanceof Mage)
				perso.setMana(perso.getMana() + 3);
			else
				perso.setMana(perso.getMana() + 1);
			if(nbTours % 3 == 0) {
				if(perso instanceof Chasseur) {
					if(((Chasseur)perso).getArme_equipee() instanceof ArmeAMunitions) {
						((ArmeAMunitions)(((Chasseur)perso).getArme_equipee())).setMunitions(((ArmeAMunitions)(((Chasseur)perso).getArme_equipee())).getMunitions() + 1);
					}
				} else if(perso instanceof Guerrier) {
					if(((Guerrier)perso).getArme_equipee() instanceof ArmeAMunitions) {
						((ArmeAMunitions)(((Guerrier)perso).getArme_equipee())).setMunitions(((ArmeAMunitions)(((Guerrier)perso).getArme_equipee())).getMunitions() + 1);
					}
				}
			}
			if(ennemi instanceof Mage)
				ennemi.setMana(ennemi.getMana() + 3);
			else
				ennemi.setMana(ennemi.getMana() + 1);
			if(nbTours % 3 == 0) {
				if(ennemi instanceof Chasseur) {
					if(((Chasseur)ennemi).getArme_equipee() instanceof ArmeAMunitions) {
						((ArmeAMunitions)(((Chasseur)ennemi).getArme_equipee())).setMunitions(((ArmeAMunitions)(((Chasseur)ennemi).getArme_equipee())).getMunitions() + 1);
					}
				} else if(ennemi instanceof Guerrier) {
					if(((Guerrier)ennemi).getArme_equipee() instanceof ArmeAMunitions) {
						((ArmeAMunitions)(((Guerrier)ennemi).getArme_equipee())).setMunitions(((ArmeAMunitions)(((Guerrier)ennemi).getArme_equipee())).getMunitions() + 1);
					}
				}
			}
			game.reprendreCombat();
		}
	}

	public void finDeTourJoueur(Game game) throws IOException, InterruptedException {
		attaqueEnnemi(game);
	}
	
}
