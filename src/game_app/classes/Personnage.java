package game_app.classes;

import game_app.weapons.Arme;
import game_app.Exceptions.CreationException;
import game_app.weapons.Sort;

import java.util.ArrayList;

public abstract class Personnage {
    protected int health, mana, level;
    protected ArrayList<Arme> weapons = new ArrayList<Arme>();
    protected ArrayList<Sort> sorts = new ArrayList<Sort>();

    public Personnage(int health, int mana, int level, ArrayList<Arme> weapons, ArrayList<Sort> sorts) throws CreationException {
        try {
            if (health > 0 || mana >= 0 || level > 0 || sorts.size() == 1 || sorts.size() == 2) {
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

    public void sort_basique(Personnage ennemi) {
        if(mana > sorts.get(0).getCout()) {
            ennemi.prendreDesDegats(sorts.get(0).getDamages());
            mana -= sorts.get(0).getCout();
        } else {
            System.out.println("Pas assez de mana");
        }
    }
}
