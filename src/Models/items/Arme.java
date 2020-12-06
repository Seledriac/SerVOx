package Models.items;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public abstract class Arme extends Item implements Serializable {

    protected int degats;

    public Arme(Accessibilite accessibilite, String nom, int cout_argent, int degats) throws CreationException {
        super(accessibilite, nom, cout_argent);
        try {
            if (degats >= 0) {
                this.degats = degats;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    @Override
    public String toString() {
        return super.toString() + "; Dégats : " + degats;
    }

}
