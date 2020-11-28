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

    public Guerrier(String nom, int health, int mana, int level, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException, GuerrierException {
        super(nom, health, mana, level, weapons, sorts);
        int i = 0, j = 0;
        try {
            if(weapons.size() <= 2) {
                for(Arme arme : weapons) {
                    if(arme instanceof Epee)
                        i++;
                    else if (arme instanceof Bouclier)
                        j++;
                }
                if(i > 1 || j > 1 || (i == 0 && j == 0))
                    throw new GuerrierException();
            } else
                throw new GuerrierException();
        } catch (GuerrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prendreDesDegats(int degats) {
        Bouclier bouclier = null;
        for(Arme arme : weapons) {
            if(arme instanceof Bouclier)
                bouclier = (Bouclier)arme;
        }
        if(degats - bouclier.getDefense() > 0)
            health -= (degats - bouclier.getDefense());
    }

    public void cac_guerrier(Personnage ennemi){
        Epee epee = null;
        for(Arme arme : weapons) {
            if(arme instanceof Epee)
                epee = (Epee)arme;
        }
        ennemi.prendreDesDegats(epee.getDamages());
    }

}
