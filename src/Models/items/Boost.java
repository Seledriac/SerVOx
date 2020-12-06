package Models.items;

import Models.Exceptions.CreationException;
import Models.classes.Personnage;

import java.io.Serializable;

public class Boost extends Sort implements Serializable {

    public Boost(Accessibilite accessibilite, String nom, int cout_argent, int cout_mana) throws CreationException {
        super(accessibilite, nom, cout_argent, cout_mana);
    }

    @Override
    public void action(Personnage lanceur, Personnage ennemi, boolean critique) {
        for(Item item : lanceur.getItems()) {
            if(item instanceof Arme) {
                if(critique)
                    ((Arme)item).setDegats(((Arme) item).getDegats() + 10);
                else
                    ((Arme)item).setDegats(((Arme) item).getDegats() + 5);
            }
        }
    }

}
