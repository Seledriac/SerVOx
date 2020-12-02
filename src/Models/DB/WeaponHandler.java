package Models.DB;

import Models.Exceptions.CreationException;
import Models.classes.Personnage;
import Models.weapons.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class WeaponHandler {

    public static final int EPEE = 1;
    public static final int BOUCLIER = 2;
    public static final int ARC = 3;
    public static final int SORT = 4;
    public static final int SORT_ULTIME = 5;

    public static void createTableWeapons() throws CreationException {
        try {
            String sql_create = " CREATE TABLE IF NOT EXISTS arme ("
                    + "	          id_arme INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "	          nom VARCHAR(50) NOT NULL UNIQUE,"
                    + "           id_type INTEGER NOT NULL"
                    + ");";
            Statement s_create = DataBase.getInstance().createStatement();
            s_create.execute(sql_create);

            String sql_insert = " REPLACE INTO arme (nom, id_type)"
                    + "	          VALUES (?, ?);";
            ArrayList<Arme> armes = new ArrayList<>();
            armes.add(new Epee("Durandal", 0));
            armes.add(new Epee("Sobek", 0));
            armes.add(new Epee("Excalibur", 0));
            armes.add(new Arc("Thor'Idal", 0, 0));
            armes.add(new Arc("Atiesh", 0, 0));
            armes.add(new Arc("Val'Anyr", 0, 0));
            armes.add(new SortUltime("Flamme Interdite", 0, 0));
            armes.add(new SortUltime("Réveil des Arcanes", 0, 0));
            armes.add(new SortUltime("Tempête", 0, 0));
            armes.add(new Sort("Petite Boule de Feu", 0, 0));
            armes.add(new Sort("Démacia", 0, 0));
            armes.add(new Sort("Traque", 0, 0));
            for(Arme arme : armes) {
                PreparedStatement s_insert = DataBase.getInstance().prepareStatement(sql_insert);
                s_insert.setString(1, arme.getNom());
                int id_type = 0;
                if(arme instanceof Epee)
                    id_type = EPEE;
                else if(arme instanceof Bouclier)
                    id_type = BOUCLIER;
                else if(arme instanceof Arc)
                    id_type = ARC;
                else if(arme instanceof Sort) {
                    if(arme instanceof SortUltime)
                        id_type = SORT_ULTIME;
                    else
                        id_type = SORT;
                }
                s_insert.setInt(2, id_type);
                s_insert.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
