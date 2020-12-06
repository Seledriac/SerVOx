package Models.items;

import Models.Exceptions.CreationException;
import Models.classes.Personnage;

import java.io.Serializable;

public abstract class Sort extends Item implements Serializable {

    protected int cout_mana;

    public Sort(Accessibilite accessibilite, String nom, int cout_argent, int cout_mana) throws CreationException {
        super(accessibilite, nom, cout_argent);
        try {
            if(cout_mana > 0) {
                this.cout_mana = cout_mana;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public int getCout_mana() {
        return cout_mana;
    }

    public void setCout_mana(int cout_mana) {
        this.cout_mana = cout_mana;
    }

    public abstract void action(Personnage lanceur, Personnage ennemi, boolean critique);

    @Override
    public String toString() {
        return super.toString() + "; Coût en mana : " + cout_mana;
    }

}
