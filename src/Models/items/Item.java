package Models.items;

import Models.Exceptions.CreationException;
import java.io.Serializable;

public abstract class Item implements Serializable {

    protected String nom;
    protected int cout_argent;
    protected Accessibilite accessibilite;

    public Item(Accessibilite accessibilite, String nom, int cout_argent) {
        try {
            if(accessibilite != null && nom != "" && cout_argent >= 0) {
                this.accessibilite = accessibilite;
                this.nom = nom;
                this.cout_argent = cout_argent;
            } else
                throw new CreationException();
        } catch(CreationException e) {
            e.printStackTrace();
        }
    }

    public Accessibilite getAccessibilite() {
        return accessibilite;
    }

    public void setAccessibilite(Accessibilite accessibilite) {
        this.accessibilite = accessibilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCout_argent() {
        return cout_argent;
    }

    public void setCout_argent(int cout_argent) {
        this.cout_argent = cout_argent;
    }

    @Override
    public String toString() {
        String acces = "";
        switch(accessibilite) {
            case GUERRIERS:
                acces += "GUERRIERS";
                break;
            case MAGES:
                acces += "MAGES";
                break;
            case CHASSEURS:
                acces += "CHASSEURS";
                break;
            default:
                break;
        }
        return "Item accessible aux " + accessibilite + "; Nom : " + nom + "; Coût en argent : " + cout_argent + "€";
    }

}
