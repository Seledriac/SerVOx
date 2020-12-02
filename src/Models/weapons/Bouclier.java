package Models.weapons;

import Models.Exceptions.CreationException;

public class Bouclier extends Arme {

    private int defense;

    public Bouclier(String nom, int damages, int defense) throws CreationException {
        super(nom, damages);
        try {
            if(defense > 0) {
                this.damages = 0;
                this.defense = defense;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return super.toString() + "; Défense : " + defense;
    }

}
