package game_app.Exceptions;

import game_app.classes.Guerrier;

public class GuerrierException extends Exception{
    public GuerrierException(){
        super("Un guerrier ne peut posséder qu'au plus une épée, un bouclier, et un sort.");
    }
}
