package Models.classes;

import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.weapons.Arme;
import Models.weapons.Bouclier;
import Models.weapons.Epee;
import Models.weapons.Sort;

import java.io.Serializable;
import java.util.ArrayList;

public class Mage extends Personnage implements Serializable {

    public Mage(String nom, int level, int health, int mana, ArrayList<Arme> weapons, ArrayList<Sort> sorts, int id_histoire) throws CreationException, MageException {
        super(nom, level, health, mana, weapons, sorts, id_histoire);
        try {
            if (weapons.size() != 0)
                throw new MageException();
        } catch (MageException e) {
            e.printStackTrace();
        }
    }

}
