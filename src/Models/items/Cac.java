package Models.items;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public abstract class Cac extends Arme implements Serializable {

    public Cac(Accessibilite accessibilite, String nom, int cout_argent, int degats) throws CreationException {
        super(accessibilite, nom, cout_argent, degats);
    }

}
