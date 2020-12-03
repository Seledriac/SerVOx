package Models.weapons;

import Models.Exceptions.CreationException;

public class Arc extends Arme {

    private int fleches;
    private int fleches_max;

    public Arc(String nom, int dammages, int fleches) throws CreationException {
        super(nom, dammages);
        try {
            if(fleches >= 0) {
                this.fleches = fleches;
                this.fleches_max = fleches;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public int getFleches() {
        return fleches;
    }

    public void setFleches(int fleches) { this.fleches = fleches; }

    public int getFleches_max() {
        return fleches_max;
    }

    public void setFleches_max(int fleches_max) {
        this.fleches_max = fleches_max;
    }

    @Override
    public String toString() {
        return super.toString() + "; Nombre de flèches : " + fleches;
    }
}
