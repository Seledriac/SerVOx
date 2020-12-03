package Models.classes;

import Models.weapons.*;
import Models.Exceptions.CreationException;

import java.util.ArrayList;

public abstract class Personnage {
    protected String nom;
    protected int health, max_health, mana, max_mana, level;
    protected ArrayList<Arme> weapons = new ArrayList<Arme>();
    protected ArrayList<Sort> sorts = new ArrayList<Sort>();
    protected Arme arme_equipee;
    protected Bouclier bouclier_equipe;

    public Personnage(String nom, int level, int health, int mana, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException {
        try {
            if (health > 0 && mana >= 0 && level > 0) {
                this.nom = nom;
                this.level = level;
                this.health = health;
                this.max_health = health;
                this.mana = mana;
                this.max_mana = mana;
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

    public Arme getArme_equipee() {
        return arme_equipee;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public void prendreDesDegats(int degats) {
        health -= degats;
        if(health < 0)
            health = 0;
    }

    public int getMax_health() {
        return max_health;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    public int getMax_mana() {
        return max_mana;
    }

    public void setMax_mana(int max_mana) {
        this.max_mana = max_mana;
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

    public void cac(Personnage ennemi, boolean critique){
        if(arme_equipee != null) {
            if(critique)
                ennemi.prendreDesDegats(arme_equipee.getDamages() * 2);
            else
                ennemi.prendreDesDegats(arme_equipee.getDamages());
        }
    }

    public void sort(Personnage ennemi, Sort sort, boolean critique) {
        if(mana > sort.getCout()) {
            if(critique)
                ennemi.prendreDesDegats(sort.getDamages() * 2);
            else
                ennemi.prendreDesDegats(sort.getDamages());
            mana -= sort.getCout();
        } else {
            if(!(this instanceof Mage)) {
                if(this instanceof Chasseur) {
                    if(((Arc)(this.getArme_equipee())).getFleches() > 0)
                        cac(ennemi, critique);
                } else
                    cac(ennemi, critique);
            } else {
                for(Sort autre_sort : sorts) {
                    if(autre_sort.getCout() < mana) {
                        sort(ennemi, autre_sort, critique);
                    }
                }
            }

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
