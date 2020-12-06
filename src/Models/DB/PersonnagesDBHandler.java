package Models.DB;

import Models.Exceptions.ChasseurException;
import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.classes.*;
import Models.items.*;

import java.sql.*;
import java.util.ArrayList;

public class PersonnagesDBHandler {

    public static void creerTablePersonnage() {
        String sql = " CREATE TABLE IF NOT EXISTS personnage ("
                + "	       nom VARCHAR(50) PRIMARY KEY,"
                + "	       argent INTEGER NOT NULL,"
                + "	       niveau INTEGER NOT NULL,"
                + "	       vie_max INTEGER NOT NULL,"
                + "	       mana_max INTEGER NOT NULL,"
                + "	       histoire INTEGER NOT NULL,"
                + "        classe INTEGER NOT NULL"
                + ");";
        try {
            Statement s = DataBase.getInstance().createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void creerTableItemPersonnage() {
        String sql = " CREATE TABLE IF NOT EXISTS item_personnage ("
                + "	       nom_item VARCHAR(50) NOT NULL,"
                + "	       nom_perso VARCHAR(50) NOT NULL,"
                + "	       degats INTEGER DEFAULT NULL,"
                + "	       cout_mana INTEGER DEFAULT NULL,"
                + "	       nb_munitions INTEGER DEFAULT NULL,"
                + "	       PRIMARY KEY (nom_item, nom_perso),"
                + "	       FOREIGN KEY (nom_item) REFERENCES item(nom),"
                + "	       FOREIGN KEY (nom_perso) REFERENCES personnage(nom)"
                + ");";
        try {
            Statement s = DataBase.getInstance().createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insererNouveauPersonnage(Personnage personnage) {
        TypePerso classe;
        if(personnage instanceof Guerrier)
            classe = TypePerso.GUERRIER;
        else if(personnage instanceof Mage)
            classe = TypePerso.MAGE;
        else
            classe = TypePerso.CHASSEUR;
        String sql = " REPLACE INTO personnage (nom, argent, niveau, vie_max, mana_max, histoire, classe)"
                + " VALUES (?, 0, 1, ?, ?, ?, ?);";
        try {
            PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
            s.setString(1, personnage.getNom());
            s.setInt(2, personnage.getVie_max());
            s.setInt(3, personnage.getMana_max());
            s.setInt(4, personnage.getHistoire().getValue());
            s.setInt(5, classe.getValue());
            s.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> listItems(String nom, TypePerso classe) {
        ArrayList<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM personnage "
                + " INNER JOIN item_personnage "
                + " ON personnage.nom = item_personnage.nom_perso"
                + " INNER JOIN item "
                + " ON item_personnage.nom_item = item.nom"
                + " WHERE personnage.nom = ?";
        try {
            PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
            s.setString(1, nom);
            ResultSet r = s.executeQuery();
            while(r.next()) {
                NatureItem nature = NatureItem.valueOf(r.getInt("nature"));
                switch(nature) {
                    case BOUCLIER:
                        items.add(new Bouclier(
                                Accessibilite.valueOf(r.getInt("accessibilite")),
                                r.getString("nom_item"),
                                r.getInt("cout_argent"),
                                r.getInt("degats")
                        ));
                        break;
                    case ARME:
                        switch(TypeArme.valueOf(r.getInt("type"))) {
                            case EPEE:
                                items.add(new Epee(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom_item"),
                                        r.getInt("cout_argent"),
                                        r.getInt("degats")
                                ));
                                break;
                            case MARTEAU:
                                items.add(new Marteau(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom_item"),
                                        r.getInt("cout_argent"),
                                        r.getInt("degats")
                                ));
                                break;
                            case ARC:
                                items.add(new Arc(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom_item"),
                                        r.getInt("cout_argent"),
                                        r.getInt("degats"),
                                        r.getInt("nb_munitions")
                                ));
                                break;
                            case SORTOFFENSIF:
                                items.add(new SortOffensif(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom_item"),
                                        r.getInt("cout_argent"),
                                        r.getInt("degats"),
                                        r.getInt("cout_mana")
                                ));
                                break;
                            default:
                                break;
                        }
                        break;
                    case SORT:
                        switch(TypeArme.valueOf(r.getInt("type"))) {
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException | CreationException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static ArrayList<Personnage> listPersonnages() {
        String sql = "SELECT * FROM  personnage ORDER BY niveau DESC;";
        ArrayList<Personnage> personnages = new ArrayList<>();
        try {
            Statement s = DataBase.getInstance().createStatement();
            ResultSet r = s.executeQuery(sql);
            while(r.next()) {
                TypePerso classe = TypePerso.valueOf(r.getInt("classe"));
                ArrayList<Item> items = listItems(r.getString("nom"), classe);
                switch (classe) {
                    case GUERRIER:
                        personnages.add(new Guerrier(
                                r.getString("nom"),
                                r.getInt("argent"),
                                r.getInt("niveau"),
                                r.getInt("vie_max"),
                                r.getInt("mana_max"),
                                items,
                                Histoire.valueOf(r.getInt("histoire"))
                        ));
                        break;
                    case MAGE:
                        personnages.add(new Mage(
                                r.getString("nom"),
                                r.getInt("argent"),
                                r.getInt("niveau"),
                                r.getInt("vie_max"),
                                r.getInt("mana_max"),
                                items,
                                Histoire.valueOf(r.getInt("histoire"))
                        ));
                        break;
                    case CHASSEUR:
                        personnages.add(new Chasseur(
                                r.getString("nom"),
                                r.getInt("argent"),
                                r.getInt("niveau"),
                                r.getInt("vie_max"),
                                r.getInt("mana_max"),
                                items,
                                Histoire.valueOf(r.getInt("histoire"))
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

    public static void updatePersonnage(Personnage personnage) {
        String sql_pers = "UPDATE personnage"
                + " SET argent=?, niveau=? , vie_max=?, mana_max=?, histoire=?"
                + " WHERE nom=?";
        String sql_items_insert = " REPLACE INTO item_personnage (nom_item, nom_perso)"
                + " VALUES(?, ?);";
        String sql_items_update = " UPDATE item_personnage"
                + " SET degats = ? , cout_mana = ?, nb_munitions = ?"
                + " WHERE nom_item=? AND nom_perso=?;";
        try {
            PreparedStatement s_pers = DataBase.getInstance().prepareStatement(sql_pers);
            s_pers.setInt(1, personnage.getArgent());
            s_pers.setInt(2, personnage.getNiveau());
            s_pers.setInt(3, personnage.getVie_max());
            s_pers.setInt(4, personnage.getMana_max());
            s_pers.setInt(5, personnage.getHistoire().getValue());
            s_pers.setString(6, personnage.getNom());
            s_pers.execute();
            for(Item item : personnage.getItems()) {
                PreparedStatement s_items_insert = DataBase.getInstance().prepareStatement(sql_items_insert);
                s_items_insert.setString(1, item.getNom());
                s_items_insert.setString(2, personnage.getNom());
                s_items_insert.execute();
                PreparedStatement s_items_update = DataBase.getInstance().prepareStatement(sql_items_update);
                if (item instanceof Bouclier) {
                    s_items_update.setInt(1, ((Bouclier) item).getDefense());
                } else if (item instanceof Arme) {
                    s_items_update.setInt(1, ((Arme) item).getDegats());
                } else {
                    s_items_update.setNull(1, java.sql.Types.INTEGER);
                }
                if (item instanceof SortOffensif || item instanceof Sort) {
                    if (item instanceof SortOffensif) {
                        s_items_update.setInt(2, ((SortOffensif) item).getCout_mana());
                    } else {
                        s_items_update.setInt(2, ((Sort) item).getCout_mana());
                    }
                } else {
                    s_items_update.setNull(2, java.sql.Types.INTEGER);
                }
                if (item instanceof ArmeAMunitions) {
                    s_items_update.setInt(3, ((Arc) item).getMunitions_max());
                } else {
                    s_items_update.setNull(3, java.sql.Types.INTEGER);
                }
                s_items_update.setString(4, item.getNom());
                s_items_update.setString(5, personnage.getNom());
                s_items_update.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
