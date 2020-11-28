package game_app.classes;

import game_app.Exceptions.CreationException;
import game_app.Exceptions.MageException;
import game_app.weapons.Arme;
import game_app.weapons.Sort;

import java.util.ArrayList;

public class Mage extends Personnage {

    public Mage(int health, int mana, int level, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException, MageException {
        super(health, mana, level, weapons, sorts);
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
