package Models.items;

import Models.Exceptions.CreationException;
import java.io.Serializable;

public class Arbalete extends ArmeAMunitions implements Serializable {

    public Arbalete(Accessibilite accessibilite, String nom, int cout_argent, int degats, int nb_munitions) throws CreationException {
        super(accessibilite, nom, cout_argent, degats, nb_munitions);
    }

    public Arbalete(Arbalete arbalete) throws CreationException {
        super(arbalete.accessibilite, arbalete.nom, arbalete.cout_argent, arbalete.degats, arbalete.munitions_max);
    }
    
}
