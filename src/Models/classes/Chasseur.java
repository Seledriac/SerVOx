package Models.classes;

import Models.Exceptions.CreationException;
import Models.Exceptions.ChasseurException;
import Models.items.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Chasseur extends Personnage implements Serializable {

    private Arme arme_equipee;
    private Bouclier bouclier_equipe;

    public Chasseur(String nom, int argent, int level, int vie, int mana, ArrayList<Item> items, Histoire histoire) throws CreationException, ChasseurException {
        super(nom, argent, level, vie, mana, items, histoire);
        try {
            for(Item item : items) {
                if(!(item.getAccessibilite() == Accessibilite.CHASSEURS)
                        && !(item.getAccessibilite() == Accessibilite.GUERRIERS_CHASSEURS)
                        && !(item.getAccessibilite() == Accessibilite.CHASSEURS_MAGES)
                        && !(item.getAccessibilite() == Accessibilite.TOUS)
                )
                    throw new ChasseurException();
                if(item instanceof Cac || item instanceof ArmeAMunitions)
                    equiperArme((Arme)item);
                if(item instanceof Bouclier)
                    equiperBouclier((Bouclier) item);
            }
        } catch (ChasseurException e) {
            e.printStackTrace();
        }
    }

    public Arme getArme_equipee() {
        return arme_equipee;
    }

    public Bouclier getBouclier_equipe() {
        return bouclier_equipe;
    }

    public void equiperArme(Arme arme_equipee) {
        if(arme_equipee.getAccessibilite() == Accessibilite.CHASSEURS
                || arme_equipee.getAccessibilite() == Accessibilite.GUERRIERS_CHASSEURS
                || arme_equipee.getAccessibilite() == Accessibilite.CHASSEURS_MAGES
                || arme_equipee.getAccessibilite() == Accessibilite.TOUS
        )
            this.arme_equipee = arme_equipee;
    }

    public void equiperBouclier(Bouclier bouclier_equipe) {
        if(bouclier_equipe.getAccessibilite() == Accessibilite.CHASSEURS
                || bouclier_equipe.getAccessibilite() == Accessibilite.GUERRIERS_CHASSEURS
                || bouclier_equipe.getAccessibilite() == Accessibilite.CHASSEURS_MAGES
                || bouclier_equipe.getAccessibilite() == Accessibilite.TOUS
        )
            this.bouclier_equipe = bouclier_equipe;

    }

    public void attaqueArme(Personnage ennemi, boolean critique){
        if(arme_equipee != null) {
            if(arme_equipee instanceof Cac) {
                if (critique)
                    ennemi.prendreDesDegats(arme_equipee.getDegats() * 2);
                else
                    ennemi.prendreDesDegats(arme_equipee.getDegats());
            } else {
                if(((ArmeAMunitions)arme_equipee).getMunitions() > 0) {
                    if (critique)
                        ennemi.prendreDesDegats(arme_equipee.getDegats() * 2);
                    else
                        ennemi.prendreDesDegats(arme_equipee.getDegats());
                    ((ArmeAMunitions)arme_equipee).setMunitions(((ArmeAMunitions)arme_equipee).getMunitions() - 1);
                } else {
                    for(Item item : items) {
                        if(item instanceof SortOffensif && ((SortOffensif) item).getCout_mana() <= mana) {
                            attaqueSort((SortOffensif)item, ennemi, critique);
                            break;
                        }
                    }
                }
            }
        } else {
            for(Item item : items) {
                if(item instanceof SortOffensif && ((SortOffensif) item).getCout_mana() <= mana) {
                    attaqueSort((SortOffensif)item, ennemi, critique);
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        String arme_eq = "";
        String bouclier_eq = "";
        if(arme_equipee != null)
            arme_eq = "; Arme équipée :" + arme_equipee.toString();
        if(arme_equipee != null)
            bouclier_eq = "; Bouclier équipé :" + arme_equipee.toString();
        return super.toString() + arme_eq + bouclier_eq;
    }

}
