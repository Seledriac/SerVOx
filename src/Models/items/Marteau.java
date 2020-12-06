package Models.items;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public class Marteau extends Cac implements Serializable {

    public Marteau(Accessibilite accessibilite, String nom, int cout_argent, int degats) throws CreationException {
        super(accessibilite, nom, cout_argent, degats);
    }

}
