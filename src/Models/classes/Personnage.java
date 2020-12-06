package Models.classes;

import Models.items.*;
import Models.Exceptions.CreationException;
import ViewModels.MainMenu;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Personnage implements Serializable {

    protected String nom;
    protected int argent, niveau, vie, vie_max, mana, mana_max;
    protected ArrayList<Item> items;
    protected Histoire histoire;

    public Personnage(String nom, int argent, int niveau, int vie, int mana, ArrayList<Item> items, Histoire histoire) throws CreationException {
        try {
            if (vie > 0 && mana >= 0 && niveau > 0 && argent >= 0) {
                this.nom = nom;
                this.niveau = niveau;
                this.vie = vie;
                this.vie_max = vie;
                this.mana = mana;
                this.mana_max = mana;
                this.items = items;
                this.histoire = histoire;
                this.argent = argent;
            } else
                throw new CreationException();
        } catch (CreationException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public boolean acheter(Item item) {
        if(argent >= item.getCout_argent()) {
            argent -= item.getCout_argent();
            items.add(item);
            return true;
        }
        return false;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public void prendreDesDegats(int degats) {
        Bouclier bouclier_equipe = null;
        if(this instanceof Guerrier) {
            bouclier_equipe = ((Guerrier)this).getBouclier_equipe();
        } else if(this instanceof Chasseur) {
            bouclier_equipe = ((Chasseur)this).getBouclier_equipe();
        }
        if(bouclier_equipe != null) {
            if (degats - bouclier_equipe.getDefense() > 0)
                vie -= (degats - bouclier_equipe.getDefense());
        } else {
            vie -= degats;
        }
        if (vie < 0)
            vie = 0;
    }

    public int getVie_max() {
        return vie_max;
    }

    public void setVie_max(int vie_max) {
        this.vie_max = vie_max;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMana_max() {
        return mana_max;
    }

    public void setMana_max(int mana_max) {
        this.mana_max = mana_max;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Histoire getHistoire() {
        return histoire;
    }

    public void setHistoire(Histoire histoire) {
        this.histoire = histoire;
    }

    public void lancerSort(Sort sort, Personnage ennemi, boolean critique) {
        sort.action(this, ennemi, critique);
    }

    public void attaqueSort(SortOffensif sort, Personnage ennemi,  boolean critique) {
        boolean reussite = false;
        if(mana > sort.getCout_mana()) {
            if(critique)
                ennemi.prendreDesDegats(sort.getDegats() * 2);
            else
                ennemi.prendreDesDegats(sort.getDegats());
            mana -= sort.getCout_mana();
        } else {
            for(Item item : items) {
                if(item instanceof SortOffensif) {
                    if(((SortOffensif)item).getCout_mana() < mana) {
                        attaqueSort((SortOffensif)item, ennemi, critique);
                        reussite = true;
                        break;
                    }
                }
            }
            if(!reussite) {
                if(this instanceof Guerrier) {
                    if(((Guerrier)this).getArme_equipee() instanceof Cac || ((Guerrier)this).getArme_equipee() instanceof ArmeAMunitions) {
                        ((Guerrier)this).attaqueArme(ennemi, critique);
                    }
                } else if(this instanceof Chasseur){
                    if(((Chasseur)this).getArme_equipee() instanceof Cac || ((Chasseur)this).getArme_equipee() instanceof ArmeAMunitions) {
                        ((Chasseur)this).attaqueArme(ennemi, critique);
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
        return classe + " => Nom : " + nom + "; Niveau : " + niveau + "; Vie : " + vie + "; Mana : " + mana + "; Items : " + items + "; Argent : " + argent + "€";
    }

}
