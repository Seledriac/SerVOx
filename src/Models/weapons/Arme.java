package Models.weapons;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public abstract class Arme implements Serializable {
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

    @Override
    public String toString() {
        String classe = "";
        if(this instanceof Epee)
            classe = "Épée";
        else if(this instanceof Bouclier)
            classe = "Bouclier";
        else if(this instanceof Arc)
            classe = "Arc";
        else if(this instanceof Sort)
            classe = "Sort";
        else
            classe = "SortUltime";
        return classe + " => Nom : " + nom + "; Dégats : " + damages;
    }
}
