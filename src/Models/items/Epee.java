package Models.items;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public class Epee extends Cac implements Serializable {

    public Epee(Accessibilite accessibilite, String nom, int cout_argent, int degats) throws CreationException {
        super(accessibilite, nom, cout_argent, degats);
    }

    public Epee(Epee epee) throws CreationException {
        super(epee.accessibilite, epee.nom, epee.cout_argent, epee.degats);
    }

    @Override
    public String toString() {
        return super.toString() + "; Type : Épée";
    }

}
