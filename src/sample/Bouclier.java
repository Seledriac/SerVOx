package sample;

public class Bouclier extends Arme{

    private int defense;

    public Bouclier(String nom,int damages,int defense) throws CreationException{
        super(nom,damages);
        if (defense>0) {
            this.defense = defense;
        }else throw new CreationException();
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
