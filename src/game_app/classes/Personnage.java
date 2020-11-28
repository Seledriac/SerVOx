package game_app.classes;

import game_app.weapons.Arme;
import game_app.Exceptions.CreationException;

import java.util.ArrayList;

public abstract class Personnage {

    protected int health, mana,level;
    protected ArrayList<Arme> weapons = new ArrayList<Arme>();

    public Personnage(int health,int mana,int level,ArrayList<Arme> weapons) throws CreationException {
        if (health>0 || mana>=0 || level>0){
        this.health=health;
        this.level=level;
        this.mana=mana;
        this.weapons=weapons;
        }else throw new CreationException();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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
}
