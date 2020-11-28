package Models.classes;

import Models.Exceptions.CreationException;
import Models.Exceptions.ChasseurException;
import Models.weapons.Arc;
import Models.weapons.Arme;
import Models.weapons.Sort;

import java.util.ArrayList;

public class Chasseur extends Personnage {

    public Chasseur(String nom, int health, int mana, int level, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException, ChasseurException {
        super(nom, health, mana, level, weapons, sorts);
        try {
            if (weapons.size() != 1 || !(weapons.get(0) instanceof Arc || sorts.size() != 1))
                throw new ChasseurException();
        } catch (ChasseurException e) {
            e.printStackTrace();
        }
    }

    public void cac_chasseur(Personnage ennemi) {
        if(((Arc)weapons.get(0)).getFleches() > 0) {
            ennemi.prendreDesDegats(weapons.get(0).getDamages());
            ((Arc)weapons.get(0)).setFleches(((Arc)weapons.get(0)).getFleches() - 1);
        } else {
            System.out.println("Plus de flèche");
        }
    }

}
