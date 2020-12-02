package Models.classes;

import Models.Exceptions.CreationException;
import Models.Exceptions.ChasseurException;
import Models.weapons.Arc;
import Models.weapons.Arme;
import Models.weapons.Sort;

import java.util.ArrayList;

public class Chasseur extends Personnage {

    public Chasseur(String nom, int level, int health, int mana, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException, ChasseurException {
        super(nom, level, health, mana, weapons, sorts);
        try {
            for(Arme arme : weapons) {
                if(!(arme instanceof Arc))
                    throw new ChasseurException();
            }
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
