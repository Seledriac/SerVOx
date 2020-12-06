package Models.items;

import Models.Exceptions.CreationException;

public abstract class ArmeAMunitions extends Arme {

    protected int munitions, munitions_max;

    public ArmeAMunitions(Accessibilite accessibilite, String nom, int cout_argent, int degats, int munitions_max) throws CreationException {
        super(accessibilite, nom, cout_argent, degats);
        try {
            if(munitions_max > 0) {
                this.munitions_max = munitions_max;
                this.munitions = munitions_max;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public int getMunitions() {
        return munitions;
    }

    public void setMunitions(int munitions) {
        this.munitions = munitions;
    }

    public int getMunitions_max() {
        return munitions_max;
    }

    public void setMunitions_max(int munitions_max) {
        this.munitions_max = munitions_max;
    }

    @Override
    public String toString() {
        return super.toString() + "; Nombre de munitions maximum : " + munitions_max;
    }

}
