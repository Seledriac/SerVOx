package Models.items;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public class Bouclier extends Item implements Serializable {

    private int defense;

    public Bouclier(Accessibilite accessibilite, String nom, int cout_argent, int defense) throws CreationException {
        super(accessibilite, nom, cout_argent);
        try {
            if(defense >= 0) {
                this.defense = defense;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public Bouclier(Bouclier bouclier) throws CreationException {
        super(bouclier.accessibilite, bouclier.nom, bouclier.cout_argent);
        this.defense = bouclier.defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return super.toString() + "; Défense : " + defense + "; Type : Bouclier";
    }

}
