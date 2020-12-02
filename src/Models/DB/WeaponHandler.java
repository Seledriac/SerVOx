package Models.DB;

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

    public static void createTableWeapons() {
        String sql = " CREATE TABLE IF NOT EXISTS arme ("
                + "	       id_arme INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "	       nom VARCHAR(50) NOT NULL,"
                + "        id_type INTEGER NOT NULL"
                + ");";
        try {
            Statement s = DataBase.getInstance().createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertNewArme(Arme arme) {
        String sql = " INSERT INTO arme(nom, id_type)"
                + " VALUES(?, ?)"
                + " WHERE NOT EXISTS(SELECT * FROM arme where nom='?');";
        try {
            int id_type = 0;
            if(arme instanceof Epee)
                id_type = EPEE;
            else if(arme instanceof Bouclier)
                id_type = BOUCLIER;
            else if(arme instanceof Arc)
                id_type = ARC;
            else
                id_type = SORT;
            PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
            s.setString(1, arme.getNom());
            s.setInt(2, id_type);
            s.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
