package game_app.classes;

import game_app.Exceptions.CreationException;
import game_app.Exceptions.ChasseurException;
import game_app.weapons.Arc;
import game_app.weapons.Arme;
import game_app.weapons.Sort;

import java.util.ArrayList;

public class Chasseur extends Personnage {

    public Chasseur(int health, int mana, int level, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException, ChasseurException {
        super(health, mana, level, weapons, sorts);
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
