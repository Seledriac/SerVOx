package sample;

import java.util.ArrayList;

public class Guerrier extends Personnage{

    public Guerrier(int health, int mana, int level, ArrayList<Arme> weapons) throws CreationException{
        super(health,mana,level,weapons);
    }

    public void attaque_cac(Arme epee, Personnage ennemi){
        ennemi.setHealth(getHealth()- epee.getDamages());
    }

    public void attaque_sort(Sort sort,Personnage ennemi){
        if (this.mana> sort.getCout()) {
            ennemi.setHealth(ennemi.getHealth() - sort.getDamages());
            this.mana -= sort.getCout();
        }else{
            System.out.println("Pas de mana");
        }
    }

}
