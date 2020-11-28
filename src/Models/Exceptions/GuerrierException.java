package Models.Exceptions;

public class GuerrierException extends Exception{
    public GuerrierException(){
        super("Un guerrier ne peut posséder qu'au plus une épée, un bouclier, et un sort.");
    }
}
