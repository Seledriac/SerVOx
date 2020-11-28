package game_app.weapons;

import game_app.Exceptions.CreationException;

public abstract class Arme {
    protected int id;
    protected String nom;
    protected int damages;
    private static int count = 0;

    public Arme(String nom, int damages) throws CreationException {
        try {
            if (damages >= 0) {
                count++;
                id = count;
                this.nom = nom;
                this.damages = damages;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDamages() {
        return damages;
    }

    public void setDamages(int dammages) {
        this.damages = dammages;
    }
}
