package Models.DB;

import Application.Main;
import Models.Exceptions.CreationException;
import Models.classes.Guerrier;
import Models.classes.Mage;
import Models.classes.Personnage;
import Models.classes.TypePerso;
import Models.items.*;
import javafx.scene.control.TableColumn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ItemsDBHandler {

    public static void creerTableItem() throws CreationException {
        try {
            String sql_create = " CREATE TABLE IF NOT EXISTS item ("
                    + "	          nom VARCHAR(50) PRIMARY KEY,"
                    + "           cout_argent INTEGER NOT NULL,"
                    + "           nature INTEGER NOT NULL,"
                    + "           type INTEGER DEFAULT NULL,"
                    + "           accessibilite INTEGER NOT NULL,"
                    + "           degats INTEGER DEFAULT NULL,"
                    + "           cout_mana INTEGER DEFAULT NULL,"
                    + "           nb_munitions INTEGER DEFAULT NULL"
                    + ");";
            Statement s_create = DataBase.getInstance().createStatement();
            s_create.execute(sql_create);
            String sql_insert = " REPLACE INTO item (nom, cout_argent, nature, type, accessibilite, degats, cout_mana, nb_munitions)"
                    + "	          VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            ArrayList<Item> items = Main.gameManager.getItems_du_jeu();
            for(Item item : items) {
                PreparedStatement s_insert = DataBase.getInstance().prepareStatement(sql_insert);
                s_insert.setString(1, item.getNom());
                s_insert.setInt(2, item.getCout_argent());
                if(item instanceof Bouclier) {
                    s_insert.setInt(3, NatureItem.BOUCLIER.getValue());
                    s_insert.setNull(4, java.sql.Types.INTEGER);
                    s_insert.setInt(6, ((Bouclier)item).getDefense());
                    s_insert.setNull(7, java.sql.Types.INTEGER);
                    s_insert.setNull(8, java.sql.Types.INTEGER);
                } else if(item instanceof Arme) {
                    s_insert.setInt(3, NatureItem.ARME.getValue());
                    s_insert.setInt(6, ((Arme)item).getDegats());
                    if(item instanceof Epee) {
                        s_insert.setInt(4, TypeArme.EPEE.getValue());
                        s_insert.setNull(7, java.sql.Types.INTEGER);
                    } else if(item instanceof Marteau) {
                        s_insert.setInt(4, TypeArme.MARTEAU.getValue());
                        s_insert.setNull(7, java.sql.Types.INTEGER);
                    } else if(item instanceof Arbalete) {
                        s_insert.setInt(4, TypeArme.ARBALETE.getValue());
                        s_insert.setNull(7, java.sql.Types.INTEGER);
                    } else if(item instanceof Arc) {
                        s_insert.setInt(4, TypeArme.ARC.getValue());
                        s_insert.setNull(7, java.sql.Types.INTEGER);
                    } else if(item instanceof SortOffensif) {
                        s_insert.setInt(4, TypeArme.SORTOFFENSIF.getValue());
                        s_insert.setInt(7, ((SortOffensif)item).getCout_mana());
                    }
                    if(item instanceof ArmeAMunitions)
                        s_insert.setInt(8, ((ArmeAMunitions)item).getMunitions_max());
                    else
                        s_insert.setNull(8, java.sql.Types.INTEGER);
                }
                else {
                    s_insert.setInt(3, NatureItem.SORT.getValue());
                    if(item instanceof Boost) {
                        s_insert.setInt(4, TypeSort.BOOST.getValue());
                    } else if(item instanceof Affaiblissement) {
                        s_insert.setInt(4, TypeSort.AFFAIBLISSEMENT.getValue());
                    }
                    s_insert.setInt(6, java.sql.Types.INTEGER);
                    s_insert.setInt(7, ((Sort)item).getCout_mana());
                    s_insert.setNull(8, java.sql.Types.INTEGER);
                }
                s_insert.setInt(5, item.getAccessibilite().getValue());
                s_insert.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> listItems() {
        Personnage personnage = Main.gameManager.getPersonnage();
        TypePerso classe;
        if(personnage instanceof Guerrier) {
            classe = TypePerso.GUERRIER;
        } else if(personnage instanceof Mage) {
            classe = TypePerso.MAGE;
        } else {
            classe = TypePerso.CHASSEUR;
        }
        ArrayList<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item"
                + " WHERE accessibilite IN (?, ?, ?)";
        try {
            PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
            switch(classe) {
                case GUERRIER:
                    s.setInt(1, Accessibilite.GUERRIERS.getValue());
                    s.setInt(2, Accessibilite.GUERRIERS_MAGES.getValue());
                    s.setInt(3, Accessibilite.GUERRIERS_CHASSEURS.getValue());
                    break;
                case MAGE:
                    s.setInt(1, Accessibilite.MAGES.getValue());
                    s.setInt(2, Accessibilite.GUERRIERS_MAGES.getValue());
                    s.setInt(3, Accessibilite.CHASSEURS_MAGES.getValue());
                    break;
                case CHASSEUR:
                    s.setInt(1, Accessibilite.CHASSEURS.getValue());
                    s.setInt(2, Accessibilite.CHASSEURS_MAGES.getValue());
                    s.setInt(3, Accessibilite.GUERRIERS_CHASSEURS.getValue());
                    break;
                default:
                    break;
            }
            ResultSet r = s.executeQuery();
            while(r.next()) {
                NatureItem nature = NatureItem.valueOf(r.getInt("nature"));
                switch(nature) {
                    case BOUCLIER:
                        items.add(new Bouclier(
                                Accessibilite.valueOf(r.getInt("accessibilite")),
                                r.getString("nom"),
                                r.getInt("cout_argent"),
                                r.getInt("degats") + personnage.getNiveau()
                        ));
                        break;
                    case ARME:
                        switch(TypeArme.valueOf(r.getInt("type"))) {
                            case EPEE:
                                items.add(new Epee(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom"),
                                        r.getInt("cout_argent"),
                                        r.getInt("degats") + personnage.getNiveau() * 2
                                ));
                                break;
                            case MARTEAU:
                                items.add(new Marteau(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom"),
                                        r.getInt("cout_argent"),
                                        r.getInt("degats") + personnage.getNiveau() * 2
                                ));
                                break;
                            case ARC:
                                items.add(new Arc(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom"),
                                        r.getInt("cout_argent"),
                                        r.getInt("degats") + personnage.getNiveau() * 3,
                                        r.getInt("nb_munitions")
                                ));
                                break;
                            case SORTOFFENSIF:
                                items.add(new SortOffensif(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom"),
                                        r.getInt("cout_argent"),
                                        r.getInt("degats") + personnage.getNiveau() * 3,
                                        r.getInt("cout_mana")
                                ));
                                break;
                            case ARBALETE:
                                items.add(new Arbalete(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom"),
                                        r.getInt("cout_argent"),
                                        r.getInt("degats") + personnage.getNiveau() * 3,
                                        r.getInt("nb_munitions")
                                ));
                                break;
                            default:
                                break;
                        }
                        break;
                    case SORT:
                        switch(TypeSort.valueOf(r.getInt("type"))) {
                            case BOOST:
                                items.add(new Boost(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom"),
                                        r.getInt("cout_argent"),
                                        r.getInt("cout_mana")
                                ));
                                break;
                            case AFFAIBLISSEMENT:
                                items.add(new Affaiblissement(
                                        Accessibilite.valueOf(r.getInt("accessibilite")),
                                        r.getString("nom"),
                                        r.getInt("cout_argent"),
                                        r.getInt("cout_mana")
                                ));
                                break;
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

}
