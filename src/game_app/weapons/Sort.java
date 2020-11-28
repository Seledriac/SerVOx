package game_app.weapons;

import game_app.Exceptions.CreationException;

public class Sort extends Arme {

    private int cout;

    public Sort(String nom,int dammages,int cout) throws CreationException {
        super(nom, dammages);
        if (cout>=0) {
            this.cout = cout;
        }else throw new CreationException();
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }
}
