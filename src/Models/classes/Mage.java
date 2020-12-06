package Models.classes;

import Models.Exceptions.CreationException;
import Models.Exceptions.GuerrierException;
import Models.Exceptions.MageException;
import Models.items.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Mage extends Personnage implements Serializable {

    public Mage(String nom, int argent, int niveau, int vie, int mana, ArrayList<Item> items, Histoire histoire) throws CreationException, MageException {
        super(nom, argent, niveau, vie, mana, items, histoire);
        try {
            for(Item item : items) {
                if (!(item.getAccessibilite() == Accessibilite.MAGES)
                        && !(item.getAccessibilite() == Accessibilite.GUERRIERS_MAGES)
                        && !(item.getAccessibilite() == Accessibilite.CHASSEURS_MAGES)
                        && !(item.getAccessibilite() == Accessibilite.TOUS)
                )
                    throw new MageException();
            }
        } catch (MageException e) {
            e.printStackTrace();
        }
    }

}
