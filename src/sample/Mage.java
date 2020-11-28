package sample;

import java.util.ArrayList;

public class Mage extends Personnage{

    public Mage(int health, int mana, int level, ArrayList<Arme> weapons) throws CreationException{
        super(health,mana,level,weapons);
    }

}
