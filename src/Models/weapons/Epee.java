package Models.weapons;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public class Epee extends Arme implements Serializable {

    public Epee(String nom, int damages) throws CreationException {
        super(nom, damages);
    }

}
