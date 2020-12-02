package Models.weapons;

import Models.Exceptions.CreationException;

public class SortUltime extends Sort {

    public SortUltime(String nom, int dammages, int cout) throws CreationException {
        super(nom, dammages, cout);
    }
}
