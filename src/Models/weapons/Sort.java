package Models.weapons;

import Models.Exceptions.CreationException;

public class Sort extends Arme {

    protected int cout;

    public Sort(String nom, int dammages, int cout) throws CreationException {
        super(nom, dammages);
        try {
            if(cout >= 0) {
                this.cout = cout;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }
}
