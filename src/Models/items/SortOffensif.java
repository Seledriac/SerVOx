package Models.items;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public class SortOffensif extends Arme implements Serializable  {

    private int cout_mana;

    public SortOffensif(Accessibilite accessibilite, String nom, int cout_argent, int degats, int cout_mana) throws CreationException {
        super(accessibilite, nom, cout_argent, degats);
        try {
            if(cout_mana >= 0) {
                this.cout_mana = cout_mana;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public SortOffensif(SortOffensif sort) throws CreationException {
        super(sort.accessibilite, sort.nom, sort.cout_argent, sort.degats);
        this.cout_mana = sort.cout_mana;
    }

    public int getCout_mana() {
        return cout_mana;
    }

    public void setCout_mana(int cout_mana) {
        this.cout_mana = cout_mana;
    }

    @Override
    public String toString() {
        return super.toString() + "; Coût en mana : " + cout_mana + "; Type : Sort Offensif";
    }

}
