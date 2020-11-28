package game_app.weapons;

import game_app.Exceptions.CreationException;

public class Arc extends Arme {

    private int fleches;

    public Arc(String nom,int dammages, int fleches) throws CreationException {
        super(nom,dammages);
        if (fleches>=0) {
            this.fleches = fleches;
        }else throw new CreationException();
    }

    public int getFleches() {
        return fleches;
    }

    public void setFleches(int fleches) {
        this.fleches = fleches;
    }
}
