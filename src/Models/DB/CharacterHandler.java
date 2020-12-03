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

	public static final int GUERRIER = 1;
	public static final int MAGE = 2;
	public static final int CHASSEUR = 3;

	/**
	 * Cette classe sert à intéragir avec la table des personnages :
	 * - La créer
	 * - Y insérer un nouveau personnage
	 * - Récupérer les personnages qui y sont présents dans une ArrayList
	 * - Ajouter des armes à un personnage
	 */

	public static void createTableCharacters() {
		String sql = " CREATE TABLE IF NOT EXISTS personnage ("
				+ "	       id_perso INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "        id_classe INTEGER NOT NULL,"
				+ "	       nom VARCHAR(50) NOT NULL UNIQUE,"
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

	public static void createTableArmePersonnage() {
		String sql = " CREATE TABLE IF NOT EXISTS arme_personnage ("
				+ "	       nom_arme VARCHAR(50) NOT NULL,"
				+ "	       nom_perso VARCHAR(50) NOT NULL,"
				+ "	       degats INTEGER NOT NULL,"
				+ "	       cout INTEGER DEFAULT NULL,"
				+ "	       nbfleches INTEGER DEFAULT NULL,"
				+ "	       PRIMARY KEY (nom_arme, nom_perso),"
				+ "	       FOREIGN KEY (nom_arme) REFERENCES arme(nom),"
				+ "	       FOREIGN KEY (nom_perso) REFERENCES personnage(nom)"
				+ ");";
		try {
			Statement s = DataBase.getInstance().createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertNewCharacter(Personnage personnage) {
		int id_classe = 0;
		if(personnage instanceof Guerrier)
			id_classe = GUERRIER;
		else if(personnage instanceof Mage)
			id_classe = MAGE;
		else
			id_classe = CHASSEUR;
		String sql = " INSERT INTO personnage (id_classe, nom, niveau, vie, mana)"
		+ " VALUES (?, ?, 1, ?, ?);";
		try {
			PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
			s.setInt(1, id_classe);
			s.setString(2, personnage.getNom());
			s.setInt(3, personnage.getMax_health());
			s.setInt(4, personnage.getMax_mana());
			s.executeUpdate();
		} catch (SQLException e) {
			System.exit(1);
		}
	}

	public static ArrayList<ArrayList<? extends Arme>> listWeapons(String nom) {
		ArrayList<ArrayList<? extends Arme>> set_res = new ArrayList<>();
		ArrayList<Arme> weapons = new ArrayList<>();
		ArrayList<Sort> sorts = new ArrayList<>();
		set_res.add(weapons);
		set_res.add(sorts);
		String sql = "SELECT * FROM personnage "
		+ " INNER JOIN arme_personnage "
		+ " ON personnage.nom = arme_personnage.nom_perso"
		+ " INNER JOIN arme "
		+ " ON arme_personnage.nom_arme = arme.nom"
		+ " WHERE personnage.nom = ?";
		try {
			int id_type_arme = 0;
			PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
			s.setString(1, nom);
			ResultSet r = s.executeQuery();
			while(r.next()) {
				id_type_arme = r.getInt("id_type");
				switch (id_type_arme) {
					case WeaponHandler.EPEE:
						weapons.add(new Epee(
								r.getString("nom_arme"),
								r.getInt("degats")
						));
						break;
					case WeaponHandler.BOUCLIER:
						weapons.add(new Bouclier(
								r.getString("nom_arme"),
								0,
								r.getInt("degats")
						));
						break;
					case WeaponHandler.ARC:
						weapons.add(new Arc(
								r.getString("nom_arme"),
								r.getInt("degats"),
								r.getInt("nbfleches")
						));
						break;
					case WeaponHandler.SORT:
						sorts.add(new Sort(
							r.getString("nom_arme"),
							r.getInt("degats"),
							r.getInt("cout")
						));
						break;
					case WeaponHandler.SORT_ULTIME:
						sorts.add(new SortUltime(
							r.getString("nom_arme"),
							r.getInt("degats"),
							r.getInt("cout")
						));
						break;
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
			ResultSet r = s.executeQuery();
			while(r.next()) {
				// Récupération des armes et sorts du personnage
				ArrayList<ArrayList<? extends Arme>> set_weapons = listWeapons(r.getString("nom"));
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

	public static void updateCharacter(Personnage personnage) {
		String sql_pers = "UPDATE personnage"
				+ " SET niveau=? , vie=?, mana=?"
				+ " WHERE nom=?";
		String sql_armes_insert = " REPLACE INTO arme_personnage (nom_arme, nom_perso, degats)"
				+ " VALUES(?, ?, 0);";
		String sql_armes_update = " UPDATE arme_personnage"
				+ " SET degats = ? , cout = ?, nbfleches = ?"
				+ " WHERE nom_arme=? AND nom_perso=?;";
		try {
			PreparedStatement s_pers = DataBase.getInstance().prepareStatement(sql_pers);
			s_pers.setInt(1, personnage.getLevel());
			s_pers.setInt(2, personnage.getMax_health());
			s_pers.setInt(3, personnage.getMax_mana());
			s_pers.setString(4, personnage.getNom());
			s_pers.execute();
			for(Arme arme : personnage.getWeapons()) {
				PreparedStatement s_armes_insert = DataBase.getInstance().prepareStatement(sql_armes_insert);
				s_armes_insert.setString(1, arme.getNom());
				s_armes_insert.setString(2, personnage.getNom());
				s_armes_insert.execute();
			}
			for(Arme arme : personnage.getWeapons()) {
				PreparedStatement s_armes_update = DataBase.getInstance().prepareStatement(sql_armes_update);
				if(arme instanceof Bouclier)
					s_armes_update.setInt(1, ((Bouclier) arme).getDefense());
				else
					s_armes_update.setInt(1, arme.getDamages());
				s_armes_update.setNull(2, java.sql.Types.INTEGER);
				if(arme instanceof Arc)
					s_armes_update.setInt(3, ((Arc)arme).getFleches());
				else
					s_armes_update.setNull(3, java.sql.Types.INTEGER);
				s_armes_update.setString(4, arme.getNom());
				s_armes_update.setString(5, personnage.getNom());
				s_armes_update.execute();
			}
			for(Sort sort : personnage.getSorts()) {
				PreparedStatement s_sorts_insert = DataBase.getInstance().prepareStatement(sql_armes_insert);
				s_sorts_insert.setString(1, sort.getNom());
				s_sorts_insert.setString(2, personnage.getNom());
				s_sorts_insert.execute();
			}
			for(Sort sort : personnage.getSorts()) {
				PreparedStatement s_sorts_update = DataBase.getInstance().prepareStatement(sql_armes_update);
				s_sorts_update.setInt(1, sort.getDamages());
				s_sorts_update.setInt(2, sort.getCout());
				s_sorts_update.setNull(3, java.sql.Types.INTEGER);
				s_sorts_update.setString(4, sort.getNom());
				s_sorts_update.setString(5, personnage.getNom());
				s_sorts_update.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
