package sample;

public abstract class Arme {
    protected String nom;
    protected int damages;

    public Arme(String nom,int damages) throws CreationException{
        if (damages>=0) {
            this.nom = nom;
            this.damages = damages;
        }else throw new CreationException();
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
