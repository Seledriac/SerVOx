package Models.DB;

import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.classes.Chasseur;
import Models.classes.Guerrier;
import Models.classes.Mage;
import Models.classes.Personnage;
import Models.weapons.*;

import java.sql.*;
import java.util.ArrayList;

public class CharacterHandler {

	private static final int GUERRIER = 1;
	private static final int MAGE = 2;
	private static final int CHASSEUR = 3;
	private static final int EPEE = 101;
	private static final int BOUCLIER = 102;
	private static final int ARC = 103;
	private static final int SORT = 104;

	/**
	 * Cette classe sert à intéragir avec la table des personnages :
	 * - La créer
	 * - Y insérer un nouveau personnage
	 * - Récupérer les personnages qui y sont présents dans une ArrayList
	 * - Ajouter des armes à un personnage
	 */

	public static void createTableCharacters() {
		// Création de la table des classes de personnage, et création de la table des personnage
		String sql = " CREATE TABLE IF NOT EXISTS personnage ("
				+ "	       id_perso INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "        id_classe INTEGER NOT NULL,"
				+ "	       nom VARCHAR(50) NOT NULL,"
				+ "	       niveau INTEGER NOT NULL,"
				+ "	       vie INTEGER NOT NULL,"
				+ "	       mana INTEGER NOT NULL"
				+ ");";
		try {
			Statement s = DataBase.getInstance().createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertNewCharacter(Personnage personnage) {
		// récupération de la date actuelle
		java.util.Date date = new java.util.Date();
		long time = date.getTime();
		String sql = " INSERT INTO personnage (id_classe, nom, niveau, vie, mana)"
		+ " VALUES(?, ?, '1', ?, ?)"
		+ " WHERE NOT EXISTS(SELECT * FROM personnage where nom='?');";
		try {
			int id_classe = 0;
			String nom_classe = personnage.getClass().getName();
			if(nom_classe == "Guerrier")
				id_classe = GUERRIER;
			else if(nom_classe == "Mage")
				id_classe = MAGE;
			else
				id_classe = CHASSEUR;
			PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
			s.setInt(1, id_classe);
			s.setString(2, personnage.getNom());
			s.setInt(3, personnage.getHealth());
			s.setInt(4, personnage.getMana());
			s.setString(5, personnage.getNom());
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<ArrayList<? extends Arme>> listWeapons(int id_perso) {
		ArrayList<ArrayList<? extends Arme>> set_res = new ArrayList<>();
		ArrayList<Arme> weapons = null;
		ArrayList<Sort> sorts = null;
		set_res.add(weapons);
		set_res.add(sorts);
		String sql = "SELECT * FROM personnage "
		+ " INNER JOIN arme_personnage "
		+ " ON personnage.id_perso = arme_personnage.id_perso"
		+ " WHERE personnage.id_perso = ?";
		try {
			int id_type_arme = 0;
			PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
			s.setInt(1, id_perso);
			ResultSet r = s.executeQuery(sql);
			while(r.next()) {
				id_type_arme = r.getInt("id_type");
				switch (id_type_arme) {
					case EPEE:
						weapons.add(new Epee(
								r.getString("arme.nom"),
								r.getInt("degats")
						));
						break;
					case BOUCLIER:
						weapons.add(new Bouclier(
								r.getString("arme.nom"),
								0,
								r.getInt("degats")
						));
						break;
					case ARC:
						weapons.add(new Arc(
								r.getString("arme.nom"),
								r.getInt("degats"),
								r.getInt("nb_fleches")
						));
						break;
					case SORT:
						sorts.add(new Sort(
							r.getString("arme.nom"),
							r.getInt("degats"),
							r.getInt("cout")
						));
					default:
						break;
				}
			}
		} catch (SQLException | CreationException e) {
			e.printStackTrace();
		}
		return set_res;
	}
	
	public static ArrayList<Personnage> listCharacters(int limit) {
		String sql = "SELECT * FROM  personnage ORDER BY niveau DESC LIMIT ?;";
		ArrayList<Personnage> personnages = new ArrayList<>();
		try {
			PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
			s.setInt(1, limit);
			ResultSet r = s.executeQuery(sql);
			while (r.next()) {
				// Récupération des armes et sorts du personnage
				ArrayList<ArrayList<? extends Arme>> set_weapons = listWeapons(r.getInt("id_perso"));
				ArrayList<Arme> weapons = (ArrayList<Arme>) set_weapons.get(0);
				ArrayList<Sort> sorts = (ArrayList<Sort>) set_weapons.get(1);
				int id_classe = r.getInt("id_classe");
				switch (id_classe) {
					case GUERRIER:
						personnages.add(new Guerrier(
								r.getString("nom"),
								r.getInt("niveau"),
								r.getInt("vie"),
								r.getInt("mana"),
								weapons,
								sorts
						));
						break;
					case MAGE:
						personnages.add(new Mage(
								r.getString("nom"),
								r.getInt("niveau"),
								r.getInt("vie"),
								r.getInt("mana"),
								weapons,
								sorts
						));
						break;
					case CHASSEUR:
						personnages.add(new Chasseur(
								r.getString("nom"),
								r.getInt("niveau"),
								r.getInt("vie"),
								r.getInt("mana"),
								weapons,
								sorts
						));
						break;
					default:
						break;
				}
			}
		} catch (SQLException | CreationException | GuerrierException | ChasseurException | MageException e) {
			e.printStackTrace();
		}
		return personnages;
	}


	
}
