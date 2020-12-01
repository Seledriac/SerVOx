package Models.weapons;

import Models.Exceptions.CreationException;

public abstract class Arme {
    protected int id_arme;
    protected String nom;
    protected int damages;

    public Arme(String nom, int damages) throws CreationException {
        try {
            if (damages >= 0) {
                this.nom = nom;
                this.damages = damages;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public int getId_arme() {
        return id_arme;
    }

    public void setId_arme(int id_arme) {
        this.id_arme = id_arme;
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
