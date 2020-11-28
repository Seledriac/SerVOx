package game_app.classes;

import game_app.Exceptions.CreationException;
import game_app.weapons.Arc;
import game_app.weapons.Arme;
import game_app.weapons.Sort;

import java.util.ArrayList;

public class Chasseur extends Personnage {

    public Chasseur(int health,int mana,int level,ArrayList<Arme> weapons) throws CreationException {
        super(health,mana,level,weapons);
    }

    public void attaque_cac(Arc arc, Personnage ennemi){
        if (arc.getFleches()>0) {
            ennemi.setHealth(ennemi.getHealth() - arc.getDamages());
            arc.setFleches(arc.getFleches() - 1);
        }else{
            System.out.println("Pas de flèche");
        }
    }

    public void attaque_sort(Sort sort, Personnage ennemi){
        if (this.mana> sort.getCout()) {
            ennemi.setHealth(ennemi.getHealth() - sort.getDamages());
            this.mana -= sort.getCout();
        }else{
            System.out.println("Pas de mana");
        }
    }

}
