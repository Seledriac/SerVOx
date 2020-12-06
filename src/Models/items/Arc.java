package Models.items;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public class Arc extends ArmeAMunitions implements Serializable {

    public Arc(Accessibilite accessibilite, String nom, int cout_argent, int degats, int fleches_max) throws CreationException {
        super(accessibilite, nom, cout_argent, degats, fleches_max);
    }

    public Arc(Arc arc) throws CreationException {
        super(arc.accessibilite, arc.nom, arc.cout_argent, arc.degats, arc.munitions_max);
    }

    @Override
    public String toString() {
        return super.toString() + "; Type : Arc";
    }

}
