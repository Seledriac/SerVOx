package Models.weapons;

import Models.Exceptions.CreationException;

public class Epee extends Arme {

    public Epee(String nom, int damages) throws CreationException {
        super(nom, damages);
    }

}
