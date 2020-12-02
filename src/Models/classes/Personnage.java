package Models.classes;

import Models.weapons.*;
import Models.Exceptions.CreationException;

import java.util.ArrayList;

public abstract class Personnage {
    protected String nom;
    protected int health, mana, level;
    protected ArrayList<Arme> weapons = new ArrayList<Arme>();
    protected ArrayList<Sort> sorts = new ArrayList<Sort>();
    protected Arme arme_equipee;
    protected Bouclier bouclier_equipe;

    public Personnage(String nom, int level, int health, int mana, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException {
        try {
            if (health > 0 && mana >= 0 && level > 0) {
                this.nom = nom;
                this.health = health;
                this.level = level;
                this.mana = mana;
                this.weapons = weapons;
                this.sorts = sorts;
            } else
                throw new CreationException();
        } catch (CreationException e) {
            e.printStackTrace();
        }
    }

    public void equiperArme(Arme arme) {
        if(arme instanceof Epee || arme instanceof Arc)
            arme_equipee = arme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getHealth() {
        return health;
    }

    public void prendreDesDegats(int degats) {
        health -= degats;
        if(health < 0)
            health = 0;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Arme> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Arme> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(ArrayList<Sort> sorts) {
        this.sorts = sorts;
    }

    public void sort_basique(Personnage ennemi) {
        if(mana > sorts.get(0).getCout()) {
            ennemi.prendreDesDegats(sorts.get(0).getDamages());
            mana -= sorts.get(0).getCout();
        } else {
            System.out.println("Pas assez de mana");
        }
    }

    @Override
    public String toString() {
        String classe = "";
        if(this instanceof Guerrier)
            classe = "Guerrier";
        else if(this instanceof Mage)
            classe = "Mage";
        else
            classe = "Chasseur";
        return classe + " => Nom : " + nom + "; Niveau : " + level + "; Vie : " + health + "; Mana : " + mana + "; Armes : " + weapons + "; Sorts : " + sorts;
    }

}
