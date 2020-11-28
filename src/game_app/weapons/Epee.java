package game_app.weapons;

import game_app.Exceptions.CreationException;

public class Epee extends Arme {

    public Epee(String nom, int damages) throws CreationException {
        super(nom, damages);
    }

}
