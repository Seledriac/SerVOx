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

    @Override
    public void cac(Personnage ennemi, boolean critique) {
        if(((Arc)arme_equipee).getFleches() > 0) {
            if(critique)
                ennemi.prendreDesDegats(arme_equipee.getDamages() * 2);
            else
                ennemi.prendreDesDegats(arme_equipee.getDamages());
            ((Arc)arme_equipee).setFleches(((Arc)arme_equipee).getFleches() - 1);
        } else {
            if(sorts.size() > 0)
                sort(ennemi, sorts.get(0), critique);
        }
    }

}
