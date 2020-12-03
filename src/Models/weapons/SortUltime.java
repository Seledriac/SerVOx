package Models.weapons;

import Models.Exceptions.CreationException;

import java.io.Serializable;

public class SortUltime extends Sort implements Serializable {

    public SortUltime(String nom, int dammages, int cout) throws CreationException {
        super(nom, dammages, cout);
    }
}
