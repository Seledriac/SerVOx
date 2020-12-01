package Models.classes;

import Models.Exceptions.CreationException;
import Models.Exceptions.MageException;
import Models.weapons.Arme;
import Models.weapons.Sort;

import java.util.ArrayList;

public class Mage extends Personnage {

    public Mage(String nom, int level, int health, int mana, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException, MageException {
        super(nom, health, mana, level, weapons, sorts);
        try {
            if (weapons.size() > 0 || sorts.size() != 2)
                throw new MageException();
        } catch (MageException e) {
            e.printStackTrace();
        }
    }

    public void sort_ultime(Personnage ennemi) {
        if(mana > sorts.get(1).getCout()) {
            ennemi.prendreDesDegats(sorts.get(1).getDamages());
            mana -= sorts.get(1).getCout();
        } else {
            System.out.println("Pas assez de mana");
        }
    }

}
