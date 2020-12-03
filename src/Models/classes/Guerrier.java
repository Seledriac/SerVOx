package Models.classes;

import Models.Exceptions.GuerrierException;
import Models.weapons.*;
import Models.Exceptions.CreationException;
import Models.weapons.Arme;
import Models.weapons.Bouclier;
import Models.weapons.Epee;
import Models.weapons.Sort;

import java.util.ArrayList;

public class Guerrier extends Personnage {

    public Guerrier(String nom, int level, int health, int mana, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException, GuerrierException {
        super(nom, level, health, mana, weapons, sorts);
        try {
            for(Arme arme : weapons) {
                if (!(arme instanceof Epee) && !(arme instanceof Bouclier))
                    throw new GuerrierException();
            }
        } catch (GuerrierException e) {
            e.printStackTrace();
        }
    }

    public void equiperBouclier(Bouclier bouclier) {
        bouclier_equipe = bouclier;
    }

    @Override
    public void prendreDesDegats(int degats) {
        if(degats - bouclier_equipe.getDefense() > 0)
            health -= (degats - bouclier_equipe.getDefense());
        if(health < 0)
            health = 0;
    }

}
